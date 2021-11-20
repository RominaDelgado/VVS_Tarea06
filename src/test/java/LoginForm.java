import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends PageObject{

    private final String USERNAME="standard_user";
    private final String PASSWORD="secret_sauce";

    private final String INVALID_USERNAME="specific_user";
    private final String INVALID_PASSWORD="secrett_sauce";

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement login_button;

    @FindBy(xpath = "//div[@class='error-message-container error']")
    private WebElement message_container_error;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(){
        this.username.sendKeys(USERNAME);
    }

    public void enterPassword(){
        this.password.sendKeys(PASSWORD);
    }

    public void pressLoginButton(){
        this.login_button.click();
    }

    public void enterInvalidUsername(){
        this.username.sendKeys(INVALID_USERNAME);
    }

    public void enterInvalidPassword(){
        this.password.sendKeys(INVALID_PASSWORD);
    }

    public String getErrorMessage(){
        return message_container_error.getText();
    }

    public String getLoginText(){
        return login_button.getAttribute("value");
    }
}