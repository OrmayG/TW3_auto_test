

import codecool.pages.IssuesPage;
import codecool.pages.LoginPage;
import codecool.pages.MainDashboardPage;
import codecool.pages.NewIssueWindow;
import org.junit.jupiter.api.AfterEach;
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
    String username = "automation67";
    String password = "CCAutoTest19.";
    String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";

    @BeforeEach
    public void SetUp(){
        LoginPage page = new LoginPage(driver,wait);
        page.login(username,password);

    }

    @ParameterizedTest
    @CsvSource({"Summary,Description,Environment"})
    public void testin(String summary, String desc, String env){

        MainDashboardPage dashboardPage = new MainDashboardPage(driver,wait);
        dashboardPage.openNewIssueWindow();
        NewIssueWindow window = new NewIssueWindow(driver,wait);
        window.newIssue(summary,desc,env);
    }

    @AfterEach
    public void TearDown(){
        driver.quit();
    }
}
