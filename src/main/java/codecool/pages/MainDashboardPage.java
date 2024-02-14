package codecool.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainDashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainDashboardPage(WebDriver driver, Duration waitTime) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, waitTime);
        PageFactory.initElements(driver,this);
    }

    @FindBy(linkText = "Projects")
    private WebElement projectDropdown;
    @FindBy(linkText = "View All Projects")
    private WebElement viewAllProjectsButton;
    @FindBy(linkText = "Software")
    private WebElement viewSoftwareProjectsButton;
    @FindBy(linkText = "Business")
    private WebElement viewBusinessProjectsButton;

    @FindBy(xpath = "//h2")
    public WebElement projectFilterType;

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

}
