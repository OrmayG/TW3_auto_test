package codecool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NewIssueWindow {
    WebDriver driver;
    JavascriptExecutor js;
    @FindBys(@FindBy(css = "iframe"))
    List<WebElement> frames;
    @FindBy(id = "mce_0_ifr")
    WebElement descFrame;
    @FindBy(id = "mce_6_ifr")
    WebElement envFrame;
    @FindBy(id = "summary")
    WebElement summaryInput;
    @FindBy(id = "create-issue-submit")
    WebElement saveNewIssueButton;
    String descId =  "mce_0_ifr";
    String envId = "mce_6_ifr";


    public NewIssueWindow(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(descId)));
        PageFactory.initElements(driver,this);

    }
    public void newIssue(String summary, String desc, String env){
        setSummary(summary);
        setDescription(desc);
        setEnvironment(env);
        //saveNewIssueButton.click();


    }
    private void setDescription(String description){


        driver.switchTo().frame(descFrame);
        WebElement html = driver.findElement(By.id("tinymce"));
        js.executeScript("arguments[0].innerHTML =arguments[1]", html,description);
        driver.switchTo().defaultContent();
    }
    private void setEnvironment(String environment){

        driver.switchTo().frame(envFrame);
        WebElement html = driver.findElement(By.id("tinymce"));
        js.executeScript("arguments[0].innerHTML =arguments[1]", html,environment);
        driver.switchTo().defaultContent();
    }
    private void saveNewIssue(){
        saveNewIssueButton.click();
    }
    private void setSummary(String summary){
        summaryInput.sendKeys(summary);
    }
    public void testin(){
        for (WebElement frame : frames) {
            System.out.println(frame.getAttribute("id"));
        }
    }
}
