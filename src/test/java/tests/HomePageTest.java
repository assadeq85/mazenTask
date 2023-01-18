package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTest extends TestsBase {

    HomePage homePage;
    String homePageUrl;

    @BeforeTest
    @Parameters({"homePageUrl"})
    public void setHomePageUrl(String url){
        homePageUrl=url;
    }

    @BeforeMethod
    public void initiateHomePage() {
        homePage = new HomePage(driver);
    }

    @Test
    @Parameters({"career", "country"})
    public void unloggedCustomerCanSearchForAJob(String career, String country) {
        homePage.navigateToHomePage(homePageUrl);
        homePage.searchForCareer(career);
        homePage.filterSearchByCountry(country);
        Assert.assertTrue(homePage.resultsList.size() > 0);
    }

    @Test
    @Parameters({"careerNotToBeFound"})
    public void customerNotifiedWhenNoOffersMatchGivenCriteria(String career) {
        homePage.navigateToHomePage(homePageUrl);
        homePage.searchForCareer(career);
        Assert.assertEquals(homePage.jobsFoundMsg.getText(), "We found 0 jobs based on your search criteria");
    }
}
