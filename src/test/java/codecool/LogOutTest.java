package codecool;

import codecool.pages.LoginPage;
import codecool.pages.MainDashboardPage;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogOutTest {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(16));

    private final static String USERNAME = System.getenv("JIRA_USERNAME");
    private final static String PASSWORD = System.getenv("PASSWORD");
    MainDashboardPage dashboardPage;
    LoginPage loginPage;


    @BeforeEach
    public void setUp(){
        loginPage = new LoginPage(driver,wait);
        loginPage.login(USERNAME,PASSWORD);
        dashboardPage = new MainDashboardPage(driver,wait);
    }
    @Test
    public void test(){
        String url = driver.getCurrentUrl();
        dashboardPage.logOut();
        driver.get(url);
        Assertions.assertTrue(loginPage.isUserOnLoginScreen());

    }
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
