package codecool.pages.projects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProjectListPage {
    private WebDriver driver;
    public ProjectListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBys(@FindBy(css = "td[class=\"cell-type-key\"]"))
    private List<WebElement> allProjects;
    @FindBy(linkText = "All project types")
    private WebElement allProjectsButton;
    @FindBy(linkText = "Software")
    private WebElement softwareFilterButton;
    @FindBy(linkText = "Business")
    private WebElement businessFilterButton;
    @FindBy(id = "project-filter-text")
    private WebElement searchInput;

    public List<String> getProjectNames() {
        return allProjects.stream().map(WebElement::getText).toList();
    }

    public void openVisibleProject(String projectName) {
        driver.findElement(By.linkText(projectName)).click();
    }

    public List<String> searchForProjects(String searchMessage) {
        searchInput.clear();
        searchInput.sendKeys(searchMessage);
        //search fetches slowly, website needs to catch up
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return getProjectNames();
    }

    public List<String> searchForProjects() {
        searchInput.clear();
        //search fetches slowly, website needs to catch up
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return getProjectNames();
    }

    public void navigateToSoftwareByProjectListPage() {
        softwareFilterButton.click();
    }
    public void navigateToBusinessByProjectListPage() {
        businessFilterButton.click();
    }

}
