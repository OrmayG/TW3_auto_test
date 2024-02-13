package codecool.tests.browseprojects;

import codecool.pages.MainDashboardPage;
import codecool.pages.projects.ProjectListPage;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BrowseProjects {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainDashboardPage dashboardPage;
    private ProjectListPage projectsPage;
    private Duration waitUntilTimeoutDuration = Duration.ofSeconds(30);
    private final String systemUnderTestUrl = "https://jira-auto.codecool.metastage.net/";

    private void checkAllSoftwareProjectsPresent(){
        System.out.println(projectsPage.getProjectNames());
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, waitUntilTimeoutDuration);
        projectsPage = new ProjectListPage(driver);
        dashboardPage = new MainDashboardPage(driver, waitUntilTimeoutDuration);

        driver.get(systemUnderTestUrl);
        driver.findElement(By.id("login-form-username")).sendKeys("automation68");
        driver.findElement(By.id("login-form-password")).sendKeys("CCAutoTest19.");
        driver.findElement(By.id("login")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/all-project-names-as-single-string.csv")
    public void browseAll(String projectNames) {
        LinkedList<String> expectedProjectNames = new LinkedList<>(Arrays.stream(projectNames.split(",")).toList());
        dashboardPage.navigateToAllProjects();

        assertEquals(expectedProjectNames, projectsPage.getProjectNames());

        driver.close();
    }

    @Test
    public void filterProjects() {
        dashboardPage.navigateToSoftwareProjects();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("COALA")));
        projectsPage.getProjectNames();

        assertEquals("Business - All categories", dashboardPage.navigateToBusinessProjects());

        driver.findElement(By.linkText("Software")).click();
        projectsPage.getProjectNames();

        driver.findElement(By.linkText("Business")).click();
        //checks presence
        driver.findElement(By.xpath("//*[contains(text(), \"Business - All categories\")]"));

        driver.findElement(By.linkText("All project types")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("COALA")));
        checkAllSoftwareProjectsPresent();

        driver.close();
    }

    @Test
    public void searchingForProjects() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
        driver.findElement(By.linkText("Projects")).click();
        driver.findElement(By.linkText("View All Projects")).click();
        WebElement search = driver.findElement(By.id("project-filter-text"));

        search.sendKeys("COALA");
        driver.findElement(By.cssSelector("a[href=\"/browse/COALA\"]"));
        search.clear();

        search.sendKeys("project");
        driver.findElement(By.cssSelector("a[href=\"/browse/COALA\"]"));
        driver.findElement(By.cssSelector("a[href=\"/browse/JETI\"]"));
        driver.findElement(By.cssSelector("a[href=\"/browse/MTP\"]"));
        driver.findElement(By.cssSelector("a[href=\"/browse/MT\"]"));
        driver.findElement(By.cssSelector("a[href=\"/browse/TOUCAN\"]"));
        driver.findElement(By.cssSelector("a[href=\"/browse/PP\"]"));

        search.clear();

        driver.close();
    }

    @Test
    public void openSearchedProject() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
        driver.findElement(By.linkText("Projects")).click();
        driver.findElement(By.linkText("View All Projects")).click();
        WebElement search = driver.findElement(By.id("project-filter-text"));

        search.sendKeys("COALA");
        driver.findElement(By.cssSelector("a[href=\"/browse/COALA\"]")).click();
        //driver.findElement(By.cssSelector("//div"));

        driver.close();
    }


}
