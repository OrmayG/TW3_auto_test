package codecool.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    @FindBy(id ="login-form-username")
    private WebElement userNameInput;
    @FindBy(id ="login-form-password")
    private WebElement userPasswordInput;
    @FindBy(id ="login")
    private WebElement loginButton;
    @FindBy(css = "#gadget-0-title")
    private WebElement loginGadget;

    public LoginPage(WebDriver driver,WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get(URL);
        PageFactory.initElements(driver,this);
    }

    public void login(String username, String password){
        setUsername(username);
        setPassword(password);
        pressLoginButton();
    }
    private void setUsername(String username){
        userNameInput.sendKeys(username);
    }
    private void setPassword(String password){
        userPasswordInput.sendKeys(password);
    }
    public void pressLoginButton(){
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
    }

    public boolean isUserOnLoginScreen() {
        return loginGadget.isDisplayed();
    }
}
