
package codecool.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IssuesPage{

    WebDriver driver;
    
    @FindBy(className = "search-button")
    WebElement searchButton;





    public IssuesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);    
    }

    public void clickSearchButton(){
        searchButton.click();     
    }
    public void getMyIssues(){

    }
}
