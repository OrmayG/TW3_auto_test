
package codecool.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class IssuesPage{

    JavascriptExecutor js;
    WebDriver driver;
    
    @FindBy(className = "search-button")
    WebElement searchButton;
    @FindBy(css =".add-criteria")
    WebElement moreSearchOptionsButton;
    @FindBy(xpath="//label[@title='Reporter']")
    WebElement addReporterCriteriaButton;
    @FindBy(xpath = "//label[@title='Current User']")
    WebElement setCurrentUserButton;
    @FindBy(id="searcher-query")
    WebElement searchInput;
    List<WebElement> searchResults;
    @FindBy(id = "opsbar-operations_more")
    WebElement moreOpsbarOpereationButton;
    @FindBy(id = "delete-issue")
    WebElement deleteIssueButton;
    @FindBy(id = "delete-issue-submit")
    WebElement deleteIssueSubmitButton;
    public IssuesPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);    
    }

    public void clickSearchButton(){
        searchButton.click();     
    }
    public  boolean issueIsPresent(String issue){
        searchForIssueBySummary(issue);
        for (WebElement searchResult : searchResults) {
           return searchResult.getText().equals(issue);
        }
        return false;
    }
    public void deleteIssue(String issue){
        searchForIssueBySummary(issue);
        moreOpsbarOpereationButton.click();
        deleteIssueButton.click();
        deleteIssueSubmitButton.click();

    }

    private void setSearchQuery(String query){
        searchInput.sendKeys(query);
    }
    private void searchForIssueBySummary(String summary){
        moreSearchOptionsButton.click();
        if(addReporterCriteriaButton.findElements(By.cssSelector("input")).getFirst().getAttribute("checked")==null){
            addReporterCriteriaButton.click();
            setCurrentUserButton.click();
        }else moreSearchOptionsButton.click();
        setSearchQuery(summary);
        //clickSearchButton();
        searchResults = driver.findElements(By.className("issue-link-summary"));


    }

}
