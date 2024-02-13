package codecool.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainDashboardPage {
    WebDriver driver;

    public MainDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
