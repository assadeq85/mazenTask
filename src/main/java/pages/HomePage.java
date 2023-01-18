package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends PagesBase {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "keywordLocation")
    WebElement searchTextBox;

    @FindBy(xpath = "//button[@title = 'Search']")
    WebElement searchButton;

    @FindBy(id = "location")
    WebElement filterByCountryTextBox;

    @FindBy(xpath = "//ul[@id = 'location_list']/li[contains(@class, 'list-group-item list-group-item-action')]")
    List<WebElement> locationsList;

    @FindBy(xpath = "//div[@class = 'list-group-item border-left-0 border-right-0 px-0 py-4']")
    public List<WebElement> resultsList;

    @FindBy(xpath = "//div[@class = 'text-muted font-italic mb-2 overflow-hidden']/span")
    public WebElement jobsFoundMsg;

    @FindBy(tagName = "ui-loading-spinner")
    public WebElement loadingSpinner;

    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public void searchForCareer(String career) {
        setElementText(searchTextBox, career);
        clickElement(searchButton);
        waitUntilLoadingCompletion();
    }

    public void filterSearchByCountry(String country) {
        setElementText(filterByCountryTextBox, country);
        boolean isLocationSelected = false;
        for (WebElement option : locationsList) {
            if (option.getText().trim().equalsIgnoreCase(country)) {
                clickElement(option);
                isLocationSelected = true;
                break;
            }
        }
        if (!isLocationSelected) {
            clickElement(locationsList.get(0));
        }
    }

    public void waitUntilLoadingCompletion(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOf(loadingSpinner));

    }

}
