package locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePageLocators {
    
    public HomePageLocators(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "keywordLocation")
    public WebElement searchTextBox;

    @FindBy(xpath = "//button[@title = 'Search']")
    public WebElement searchButton;

    @FindBy(id = "location")
    public WebElement filterByCountryTextBox;

    @FindBy(xpath = "//ul[@id = 'location_list']/li[contains(@class, 'list-group-item list-group-item-action')]")
    public List<WebElement> locationsList;

    @FindBy(xpath = "//div[@class = 'list-group-item border-left-0 border-right-0 px-0 py-4']")
    public List<WebElement> resultsList;

    @FindBy(xpath = "//div[@class = 'text-muted font-italic mb-2 overflow-hidden']/span")
    public WebElement jobsFoundMsg;

    @FindBy(tagName = "ui-loading-spinner")
    public WebElement loadingSpinner;
}
