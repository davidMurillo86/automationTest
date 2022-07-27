package pages.youtube;

import base.HelperMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class YoutubeVideoPage {

    private final By videoTitle = By.xpath("//h1[@class='title style-scope ytd-video-primary-info-renderer']");
    private final By skipButton = By.cssSelector(".ytp-ad-text.ytp-ad-skip-button-text");

    private final HelperMethods helperMethods;
    private static final Logger logger = LogManager.getLogger(YoutubeVideoPage.class);

    public YoutubeVideoPage(HelperMethods helperMethods){
        this.helperMethods = helperMethods;
    }

    public String getVideoTitle(){
        logger.info("Getting video title");
        return helperMethods.getElementText(videoTitle);
    }

    public void skipAdsIfPresentInVideo(){
        if(helperMethods.checkVisibilityOfElement(skipButton)){
            logger.info("Skipping video");
            helperMethods.click(skipButton);
        }
    }

}
