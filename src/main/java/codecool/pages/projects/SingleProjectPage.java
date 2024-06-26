package codecool.pages.projects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SingleProjectPage {
    private WebDriver driver;
    public SingleProjectPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[class='aui-item project-title'] > a")
    private WebElement projectName;

    public String getCurrentProjectName() {
        return projectName.getAttribute("title");
    }

}
