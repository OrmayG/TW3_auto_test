package codecool;

import codecool.pages.LoginPage;
import codecool.pages.MainDashboardPage;
import codecool.pages.projects.ProjectListPage;
import codecool.pages.projects.SingleProjectPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowseProjectsTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainDashboardPage dashboardPage;
    private ProjectListPage projectsPage;
    private LoginPage loginPage;
    private final static String USERNAME = System.getenv("JIRA_USERNAME");
    private final static String PASSWORD = System.getenv("PASSWORD");
    private final static Duration WAIT_UNTIL_TIMEOUT_DURATION = Duration.ofSeconds(10);
    private final static String SYSTEM_UNDER_TEST_URL = "https://jira-auto.codecool.metastage.net/";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, WAIT_UNTIL_TIMEOUT_DURATION);
        projectsPage = new ProjectListPage(driver);
        dashboardPage = new MainDashboardPage(driver, wait);
        loginPage = new LoginPage(driver, wait);

        loginPage.login(USERNAME, PASSWORD);
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
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/software-project-names-as-single-string.csv")
    public void filterForSoftwareProjectsThroughDashboard(String projectNames) {
        LinkedList<String> expectedProjectNames = new LinkedList<>(Arrays.stream(projectNames.split(",")).toList());
        dashboardPage.navigateToAllProjects();

        assertEquals(expectedProjectNames, projectsPage.getProjectNames());
    }
    @Test
    public void filterForBusinessProjectsThroughDashboard() {
        dashboardPage.navigateToBusinessProjects();
        assertTrue(projectsPage.getProjectNames().isEmpty());
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/software-project-names-as-single-string.csv")
    public void filterForSoftwareProjectsThroughProjectsPage(String projectNames) {
        LinkedList<String> expectedProjectNames = new LinkedList<>(Arrays.stream(projectNames.split(",")).toList());
        dashboardPage.navigateToAllProjects();
        projectsPage.navigateToSoftwareByProjectListPage();

        assertEquals(expectedProjectNames, projectsPage.getProjectNames());
    }
    @Test
    public void filterForBusinessProjectsThroughProjectsPage() {
        dashboardPage.navigateToAllProjects();
        projectsPage.navigateToBusinessByProjectListPage();
        assertTrue(projectsPage.getProjectNames().isEmpty());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/project-search-data.csv")
    public void searchForProjects(String searchData, String projectNamesAsSingleString) {
        LinkedList<String> expectedProjectNames = new LinkedList<>(Arrays.stream(projectNamesAsSingleString.split(",")).toList());
        dashboardPage.navigateToAllProjects();

        assertEquals(expectedProjectNames, projectsPage.searchForProjects(searchData));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/project-search-and-open-data.csv")
    public void openSearchedProject(String projectName) {
        SingleProjectPage oneProjectPage = new SingleProjectPage(driver);

        dashboardPage.navigateToAllProjects();
        projectsPage.searchForProjects(projectName);
        projectsPage.openVisibleProject(projectName);

        assertEquals(projectName, oneProjectPage.getCurrentProjectName());

    }


}
