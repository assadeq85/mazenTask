package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PagesBase {

    protected WebDriver driver;

    public PagesBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected static void clickElement(WebElement webElement) {
        webElement.click();
    }

    protected static void setElementText(WebElement webElement, String input) {
        webElement.sendKeys(input);
    }
}
