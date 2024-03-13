package codecool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainDashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainDashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="issues_new_search_link")
    private WebElement issuesButton;
    @FindBy(id="find_link_content")
    private WebElement issuesTab;
    @FindBy(id = "create_link")
    private WebElement createNewIssueButton;
    @FindBy(linkText = "Projects")
    private WebElement projectDropdown;
    @FindBy(linkText = "View All Projects")
    private WebElement viewAllProjectsButton;
    @FindBy(linkText = "Software")
    private WebElement viewSoftwareProjectsButton;
    @FindBy(linkText = "Business")
    private WebElement viewBusinessProjectsButton;
    @FindBy(xpath = "//img[contains(@alt, 'User')]")
    private WebElement profilePicture;
    @FindBy(css = "#log_out")
    private WebElement logOutOption;
    @FindBy(xpath = "//h2")
    private WebElement projectFilterType;

    public void logOut() {
        profilePicture.click();
        logOutOption.click();
    }
    public void navigateToIssuesPage(){
        issuesButton.click();
        issuesTab.click();
    }
    public void openNewIssueWindow(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("create_link")));
        createNewIssueButton.click();
    }
    public boolean isProfilePicturePresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(profilePicture));
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public String navigateToAllProjects() {
        wait.until(ExpectedConditions.visibilityOf(projectDropdown));
        projectDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(viewAllProjectsButton));
        viewAllProjectsButton.click();
        return projectFilterType.getText();
    }

    public String navigateToSoftwareProjects() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
        projectDropdown.click();
        viewSoftwareProjectsButton.click();
        return projectFilterType.getText();
    }

    public String navigateToBusinessProjects() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
        projectDropdown.click();
        viewBusinessProjectsButton.click();
        return projectFilterType.getText();
    }
}
