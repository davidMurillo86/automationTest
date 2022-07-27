package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.youtube.YoutubePage;
import pages.youtube.YoutubeResultsPage;
import pages.youtube.YoutubeVideoPage;

public class AnotherTest extends BaseTest {

    private YoutubePage youtubePage;
    private YoutubeResultsPage youtubeResultsPage;
    private YoutubeVideoPage youtubeVideoPage;
    private static final Logger logger = LogManager.getLogger(AnotherTest.class);

    @Test(priority = 1, description = "youtube video test")
    public void youtubeSearch() {
        logger.info("Test - Youtube search");
        driver.get(config.testYoutubeURL());
        helperMethods.waitUntilPageIsLoaded();
        youtubePage = new YoutubePage(helperMethods);
        youtubeResultsPage = new YoutubeResultsPage(helperMethods);
        youtubeVideoPage = new YoutubeVideoPage(helperMethods);
        youtubePage.searchVideo("Metallica");
        youtubeResultsPage.selectVideoWithTitle("Master of Puppets");
        youtubeVideoPage.skipAdsIfPresentInVideo();
        Assert.assertTrue(youtubeVideoPage.getVideoTitle().contains("Master of Puppets"));
    }
}
