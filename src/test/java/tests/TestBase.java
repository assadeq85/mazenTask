package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestBase {
    public static WebDriver driver;

    @BeforeSuite
    @Parameters({"browser"})
    public void initiateDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "firefox" -> driver = new FirefoxDriver();
            case "ie" -> driver = new InternetExplorerDriver();
            case "safari" -> driver = new SafariDriver();
            case "edge" -> driver = new EdgeDriver();
            default -> driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void takeScreenshotForFailedTest(ITestResult testResult) throws IOException {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMddHHmmss");
            FileUtils.copyFile(source,
                    new File("screenshots/" + testResult.getName() + dtf.format(LocalDateTime.now()) + ".png"));
        }
    }

    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }

}
