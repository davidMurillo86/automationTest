package pages.youtube;

import base.HelperMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class YoutubeResultsPage {

    private final By videoTitleLink = By.xpath("//a[@class='yt-simple-endpoint style-scope ytd-video-renderer']");

    private final HelperMethods helperMethods;
    private static final Logger logger = LogManager.getLogger(YoutubeResultsPage.class);

    public YoutubeResultsPage(HelperMethods helperMethods) {
        this.helperMethods = helperMethods;
    }

    public void selectVideoWithTitle(String text) {
        logger.info("Selecting video with title {}", text);
        WebElement video = getVideoWithTitle(text);
        helperMethods.scrollToElement(video);
        helperMethods.click(video);
        helperMethods.waitUntilPageIsLoaded();
    }

    private WebElement getVideoWithTitle(String text) {
        List<WebElement> videoLinks = helperMethods.getElements(videoTitleLink);
        List<WebElement> titles = videoLinks.stream().filter(x -> x.getAttribute("title").contains(text)).collect(Collectors.toList());

        if (!titles.isEmpty()) {
            return titles.get(0); //always use the first result
        } else {
            throw new RuntimeException(String.format("No titles found for %s video", text));
        }
    }
}
