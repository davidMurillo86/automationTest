package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.bing.BingPage;
import pages.google.GooglePage;

public class ExampleTest extends BaseTest {

    private GooglePage googlePage;
    private BingPage bingPage;

    private static final Logger logger = LogManager.getLogger(ExampleTest.class);

    @Test(priority = 1, description = "test google search")
    public void googleSearch() {
        logger.info("Test - Google search");
        driver.get(config.testChromeURL());
        helperMethods.waitUntilPageIsLoaded();
        googlePage = new GooglePage(helperMethods);
        googlePage.search("Selenium");
        Assert.assertEquals(googlePage.getResultHeaderText("Selenium"), "Selenium");
    }

    @Test(priority = 2, description = "test bing search")
    public void bingSearch() {
        logger.info("Test - Bing search");
        driver.get(config.testBingURL());
        bingPage = new BingPage(helperMethods);
        helperMethods.waitUntilPageIsLoaded();
        bingPage.search("testng");
        Assert.assertEquals(bingPage.getResultHeaderText("TestNG - Welcome"), "TestNG - Welcome");
    }
}
