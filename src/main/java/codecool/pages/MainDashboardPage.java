package codecool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

public class MainDashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id="issues_new_search_link")
    WebElement issuesButton;
    @FindBy(id="find_link_content")
    WebElement issuesTab;
    @FindBy(id = "create_link")
    WebElement createNewIssueButton;
    public MainDashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("create_link")));
        PageFactory.initElements(driver,this);
    }

    public void navigateToIssuesPage(){
        issuesButton.click();
        issuesTab.click();
    }
    public void openNewIssueWindow(){

        createNewIssueButton.click();
    }
}
