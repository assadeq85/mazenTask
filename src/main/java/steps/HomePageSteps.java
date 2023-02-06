package steps;

import locators.HomePageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePageSteps {

    HomePageLocators locators;
    private final WebDriver driver;

    public HomePageSteps(WebDriver driver) {
        this.driver = driver;
        locators = new HomePageLocators(driver);
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public void waitUntilLoadingCompletion() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOf(locators.loadingSpinner));

    }

    public void searchForCareer(String career) {
        locators.searchTextBox.sendKeys(career);
        locators.searchButton.click();
        waitUntilLoadingCompletion();
    }

    public void filterSearchByCountry(String country) {
        locators.filterByCountryTextBox.sendKeys(country);
        boolean isLocationSelected = false;
        for (WebElement option : locators.locationsList) {
            if (option.getText().trim().equalsIgnoreCase(country)) {
                option.click();
                isLocationSelected = true;
                break;
            }
        }
        if (!isLocationSelected) {
            locators.locationsList.get(0).click();
        }
    }

    public void assertResultsReturned() {
        Assert.assertTrue(locators.resultsList.size() > 0, "Assertion failed, jobs NOT found for your search entry");
    }

    public void assertNoJobsFound() {
        Assert.assertEquals(locators.jobsFoundMsg.getText(), "We found 0 jobs based on your search criteria", "Assertion failed, jobs FOUND for your search entry");
    }
}
