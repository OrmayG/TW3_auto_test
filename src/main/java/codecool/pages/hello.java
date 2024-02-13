package codecool.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class hello {

    private final WebDriver driver;

    public hello(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
}
