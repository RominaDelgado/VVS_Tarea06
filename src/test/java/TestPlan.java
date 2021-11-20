import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPlan {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Login Successfully")
    public static void loginSuccessfully(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm =  new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getTitle(),"PRODUCTS");
    }

    @Test(testName = "Add one item to cart")
    public static void verifyItemAdded(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm =  new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCartBackpack();
        Assert.assertEquals(productPage.getCardBadge(),"1");
    }

    @Test(testName = "Credenciales incorrectas - Verificar login no válido (TC 1)")
    public static void loginFails(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm =  new LoginForm(driver);
        loginForm.enterInvalidUsername();
        loginForm.enterInvalidPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(loginForm.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
    }

    @Test(testName = "Desloguearse correctamente (TC 2)")
    public static void logoutSuccessfully(){
        driver.get(Utils.BASE_URL);
        LoginForm loginForm =  new LoginForm(driver);
        loginForm.enterUsername();
        loginForm.enterPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ProductPage productPage = new ProductPage(driver);
        productPage.menuButton();
        productPage.logoutButton();
        Assert.assertEquals(loginForm.getLoginText(),"Login");
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}