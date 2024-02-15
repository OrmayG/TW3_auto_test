package codecool;

import codecool.pages.LoginPage;
import codecool.pages.MainDashboardPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private MainDashboardPage dashboardPage;
    private final static Duration TIMEOUT_DURATION = Duration.ofSeconds(10);
    private final static String SYSTEM_UNDER_TEST_URL = "https://jira-auto.codecool.metastage.net/";
    private final static String USERNAME = System.getenv("JIRA_USERNAME");
    private final static String PASSWORD = System.getenv("PASSWORD");

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, TIMEOUT_DURATION);
        loginPage = new LoginPage(driver, wait);
        dashboardPage = new MainDashboardPage(driver, wait);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void successfulLogin() {
        loginPage.login(USERNAME, PASSWORD);
        assertTrue(dashboardPage.isProfilePicturePresent());
    }

    @Test
    public void emptyCredentials() {
        loginPage.pressLoginButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loginPage.pressLoginButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loginPage.pressLoginButton();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(loginPage.isUserOnLoginScreen());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/wrong-passwords.csv")
    public void wrongPassword(String wrongPassword) {
        loginPage.login(USERNAME, wrongPassword);
        assertTrue(loginPage.isUserOnLoginScreen());
    }

    @Test
    public void isPasswordInputCaseSensitive() {
        loginPage.login(USERNAME, PASSWORD.toUpperCase());
        assertTrue(loginPage.isUserOnLoginScreen());
    }

    @Test
    public void captchaAfterThirdBadLoginAttempt() {
        loginPage.login(USERNAME, "no access");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loginPage.login(USERNAME, "no access");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        loginPage.login(USERNAME, "no access");

        assertTrue(loginPage.isCaptchaDisplayed());
    }
}
