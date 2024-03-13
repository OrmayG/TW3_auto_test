package codecool;

import codecool.pages.LoginPage;
import codecool.pages.MainDashboardPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTests {

    private WebDriver chromeDriver;
    private WebDriver firefoxDriver;
    private WebDriver edgeDriver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private MainDashboardPage dashboardPage;
    private final static int TIMEOUT_MILLIS = 1000;
    private final static String SYSTEM_UNDER_TEST_URL = "https://jira-auto.codecool.metastage.net/";
    //HOST_URL: "http://localhost:4444/wd/hub"
    private final static String HOST_URL = System.getenv("HOST_URL");
    private final static String USERNAME = System.getenv("JIRA_USERNAME");
    private final static String PASSWORD = System.getenv("PASSWORD");

    @BeforeEach
    public void setUp() {
        ChromeOptions chromeOpt = new ChromeOptions();
        //FirefoxOptions firefoxOpt = new FirefoxOptions();
        //EdgeOptions edgeOpt = new EdgeOptions();
        try {
            chromeDriver = new RemoteWebDriver(new URL(HOST_URL), chromeOpt);
            //firefoxDriver = new RemoteWebDriver(new URL(HOST_URL), firefoxOpt);
            //edgeDriver = new RemoteWebDriver(new URL(HOST_URL), edgeOpt);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        wait = new WebDriverWait(chromeDriver, Duration.ofMillis(TIMEOUT_MILLIS));
        loginPage = new LoginPage(chromeDriver, wait);
        dashboardPage = new MainDashboardPage(chromeDriver, wait);
    }

    @AfterEach
    public void tearDown() {
        if(dashboardPage.isProfilePicturePresent()) {
            dashboardPage.logOut();
        }
        chromeDriver.quit();
    }

    @Test
    @Order(1)
    public void successfulLogin() {
        loginPage.login(USERNAME, PASSWORD);
        assertTrue(dashboardPage.isProfilePicturePresent());
    }

    @Test
    @Order(2)
    public void emptyCredentials() {
        loginPage.pressLoginButton();
        assertTrue(loginPage.isLoginLinkVisible());
    }

    @Test
    @Order(3)
    public void isPasswordInputCaseSensitive() {
        loginPage.login(USERNAME, PASSWORD.toUpperCase());
        assertTrue(loginPage.isLoginLinkVisible());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/wrong-passwords.csv")
    @Order(4)
    public void wrongPassword(String wrongPassword) {
        loginPage.login(USERNAME, wrongPassword);
        assertTrue(loginPage.isLoginLinkVisible());
    }

    @Test
    @Order(5)
    public void captchaAfterThirdBadLoginAttempt() {
        loginPage.login(USERNAME, "no access");
        sleep();
        loginPage.login(USERNAME, "no access");
        sleep();
        loginPage.login(USERNAME, "no access");

        assertTrue(loginPage.isCaptchaDisplayed());
    }

    private static void sleep() {
        try {
            Thread.sleep(TIMEOUT_MILLIS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
