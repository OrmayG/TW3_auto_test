
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
    @FindBy(className="criteria-actions")
    WebElement moreButton;
    @FindBy(id="reporter-1")
    WebElement reportCriteriaButton;
    @FindBy(id="issue_current_user-2")
    WebElement currentUserButton;




    public IssuesPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        PageFactory.initElements(driver,this);    
    }

    public void clickSearchButton(){
        searchButton.click();     
    }
    public  boolean issueIsPresent(String issue){
        //TODO
        return true;
    }
    public void deleteIssue(String issue){
        //TODO
    }
    public void
}
