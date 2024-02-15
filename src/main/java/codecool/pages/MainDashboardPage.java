package codecool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainDashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    public MainDashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="issues_new_search_link")
    WebElement issuesButton;
    @FindBy(id="find_link")
    WebElement issuesTab;
    @FindBy(id = "create_link")
    WebElement createNewIssueButton;
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
    @FindBy(xpath = "//h2")
    public WebElement projectFilterType;
    @FindBy(id = "log_out")
    private WebElement logOutButton;
    public void navigateToIssuesPage(){
        issuesTab.click();
        issuesButton.click();

    }
    public void openNewIssueWindow(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("create_link")));
        createNewIssueButton.click();
    }
    public boolean isProfilePicturePresent() {
        wait.until(ExpectedConditions.visibilityOf(profilePicture));
        return profilePicture.isDisplayed();
    }
    public String navigateToAllProjects() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Projects")));
        projectDropdown.click();
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
    public void logOut(){
        profilePicture.click();
        logOutButton.click();
    }
}
