package codecool.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    @FindBy(id ="login-form-username")
    WebElement userNameInput;
    @FindBy(id ="login-form-password")
    WebElement userPasswordInput;
    @FindBy(id ="login")
    WebElement loginButton;

    public LoginPage(WebDriver driver,WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        driver.get(URL);
        PageFactory.initElements(driver,this);
    }

    public void login(String username, String password){
        setUsername(username);
        setPassword(password);
        login();
    }
    private void setUsername(String username){
        userNameInput.sendKeys(username);
    }
    private void setPassword(String password){
        userPasswordInput.sendKeys(password);
    }
    private void login(){
        loginButton.click();
    }
}
