package codecool;

import codecool.pages.IssuesPage;
import codecool.pages.LoginPage;
import codecool.pages.MainDashboardPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IssueIsPresentTests {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    String username = "automation67";
    String password = "CCAutoTest19.";

    @BeforeEach
    public void setUp(){
        new LoginPage(driver,wait).login(username,password);
        new MainDashboardPage(driver,wait).navigateToIssuesPage();
    }

    @ParameterizedTest
    @CsvSource({"Summary"})
    public void test(String search){
        IssuesPage page = new IssuesPage(driver,wait);
    }




}
