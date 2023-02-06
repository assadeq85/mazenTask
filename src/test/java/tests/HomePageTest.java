package tests;

import data.ExcelReader;
import org.testng.annotations.*;
import steps.HomePageSteps;

import java.io.IOException;

public class HomePageTest extends TestBase {

    HomePageSteps steps;
    String homePageUrl;

    public String getTestData_setHomePageUrl() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        Object[][] data = excelReader.getExcelData(0,1);
        return String.valueOf(data[0][0]);
    }

    @DataProvider(name="TestData_unloggedCustomerCanSearchForAJob")
    public Object[][] getTestData_unloggedCustomerCanSearchForAJob() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        return excelReader.getExcelData(1,2);
    }

    @DataProvider(name="TestData_customerNotifiedWhenNoOffersMatchGivenCriteria")
    public Object[][] getTestData_customerNotifiedWhenNoOffersMatchGivenCriteria() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        return excelReader.getExcelData(2,1);
    }

    @BeforeTest
    public void setHomePageUrl() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        Object[][] data = excelReader.getExcelData(0,1);
        homePageUrl = String.valueOf(data[0][0]);
    }
    @BeforeMethod
    public void initiateHomePage() {
        steps = new HomePageSteps(driver);
    }

    @Test(dataProvider = "TestData_unloggedCustomerCanSearchForAJob")
    public void unloggedCustomerCanSearchForAJob(String career, String country) {
        steps.navigateToHomePage(homePageUrl);
        steps.searchForCareer(career);
        steps.filterSearchByCountry(country);
        steps.assertResultsReturned();
    }

    @Test(dataProvider = "TestData_customerNotifiedWhenNoOffersMatchGivenCriteria")
  //  @Parameters({"careerNotToBeFound"})
    public void customerNotifiedWhenNoOffersMatchGivenCriteria(String careerNotToBeFound) {
        steps.navigateToHomePage(homePageUrl);
        steps.searchForCareer(careerNotToBeFound);
        steps.assertNoJobsFound();
    }
}

