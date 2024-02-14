

import codecool.pages.IssuesPage;
import codecool.pages.MainDashboardPage;
import codecool.pages.NewIssueWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CreateNewIssue {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    IssuesPage issuesPage;
    String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";

    @BeforeEach
    public void SetUp(){
        driver.get(URL);
        driver.findElement(By.id("login-form-username")).sendKeys("automation67");
        driver.findElement(By.id("login-form-password")).sendKeys("CCAutoTest19.");
        driver.findElement(By.id(("login"))).click();
    }

    @ParameterizedTest
    @CsvSource({"Summary,Description,Environment"})
    public void testin(String summary, String desc, String env){

        MainDashboardPage dashboardPage = new MainDashboardPage(driver,wait);
        dashboardPage.openNewIssueWindow();
        NewIssueWindow window = new NewIssueWindow(driver,wait);
        window.newIssue(summary,desc,env);
    }
}
