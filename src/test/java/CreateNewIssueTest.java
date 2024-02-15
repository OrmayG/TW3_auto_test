import codecool.pages.IssuesPage;
import codecool.pages.LoginPage;
import codecool.pages.MainDashboardPage;
import codecool.pages.NewIssueWindow;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.systeminfo.model.ProcessInfo;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CreateNewIssueTest {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    MainDashboardPage dashboardPage;
    IssuesPage issuesPage;
    NewIssueWindow issueWindow;
    String username = System.getenv("JIRA_USERNAME");

    String password = System.getenv("JIRA_PASSWORD");

    @BeforeEach
    public void SetUp(){

        LoginPage page = new LoginPage(driver,wait);
        driver.manage().window().maximize();
        page.login(username,password);
        dashboardPage = new MainDashboardPage(driver,wait);
        dashboardPage.openNewIssueWindow();
        issueWindow = new NewIssueWindow(driver,wait);

    }

    @ParameterizedTest
    @CsvSource({"ORMAYGERGELYXDRARW,Description,Environment"})
    public void newIssue(String summary, String desc, String env){
        issueWindow.newIssue(summary,desc,env);
        dashboardPage.navigateToIssuesPage();
        issuesPage = new IssuesPage(driver,wait);
        Assertions.assertTrue(issuesPage.issueIsPresent(summary));
        issuesPage.deleteIssue(summary);

    }

    @AfterEach
    public void TearDown(){
        //driver.quit();
    }
}
