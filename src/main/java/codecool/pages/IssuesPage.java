
package codecool.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssuesPage{

    WebDriver driver;
    
    @FindBy(className = "search-button")
    WebElement searchButton;





    public IssuesPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        PageFactory.initElements(driver,this);    
    }

    public void clickSearchButton(){
        searchButton.click();     
    }
    public void getMyIssues(){

    }
}
