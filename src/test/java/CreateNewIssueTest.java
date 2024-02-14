

import codecool.pages.IssuesPage;
import codecool.pages.LoginPage;
import codecool.pages.MainDashboardPage;
import codecool.pages.NewIssueWindow;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateNewIssueTest {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    MainDashboardPage dashboardPage;
    IssuesPage issuesPage;
    NewIssueWindow issueWindow;
    String username = "";
    String password = "";
    String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";

    @BeforeEach
    public void SetUp(){
        LoginPage page = new LoginPage(driver,wait);
        page.login(username,password);
        dashboardPage = new MainDashboardPage(driver,wait);
        dashboardPage.openNewIssueWindow();
        issueWindow = new NewIssueWindow(driver,wait);

    }

    @ParameterizedTest
    @CsvSource({"Summary,Description,Environment"})
    public void newIssue(String summary, String desc, String env){

        issueWindow.newIssue(summary,desc,env);

    }

    @ParameterizedTest
    @CsvSource({"Summary,Description,Environment"})
    public void issueIsCreated(String summary){
        new MainDashboardPage(driver,wait).navigateToIssuesPage();
        IssuesPage page = new IssuesPage(driver,wait);
        System.out.println(summary);
    }
    @AfterEach
    public void TearDown(){
        driver.quit();
    }
}
