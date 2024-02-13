package codecool.pages.projects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectListPage {
    private WebDriver driver;
    public ProjectListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "a[href=\"/browse/COALA\"]")
    private WebElement coalaProject;
    @FindBy(css = "a[href=\"/browse/DEMO\"]")
    private WebElement demoProject;
    @FindBy(css = "a[href=\"/browse/JETI\"]")
    private WebElement jetiProject;
    @FindBy(css = "a[href=\"/browse/MTP\"]")
    private WebElement mainTestingProject;
    @FindBy(css = "a[href=\"/browse/MT\"]")
    private WebElement manualTestingProject;
    @FindBy(css = "a[href=\"/browse/SI\"]")
    private WebElement secretIngredientProject;
    @FindBy(css = "a[href=\"/browse/FORCE\"]")
    private WebElement forceProject;
    @FindBy(css = "a[href=\"/browse/TOUCAN\"]")
    private WebElement toucanProject;
    @FindBy(css = "a[href=\"/browse/PP\"]")
    private WebElement practiceProject;
    @FindBy(linkText = "All project types")
    private WebElement allProjectsButton;
    @FindBy(linkText = "Software")
    private WebElement softwareFilterButton;
    @FindBy(linkText = "Business")
    private WebElement businessFilterButton;

}
