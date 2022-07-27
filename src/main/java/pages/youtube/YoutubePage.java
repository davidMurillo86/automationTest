package pages.youtube;

import base.HelperMethods;
import org.openqa.selenium.By;

public class YoutubePage {

    private By searchBox = By.xpath("//input[@id='search']");
    private By searchButton = By.id("search-icon-legacy");
    private By searchFormSubmit = By.id("search-form");

    private final HelperMethods helperMethods;

    public YoutubePage(HelperMethods helperMethods) {
        this.helperMethods = helperMethods;
    }

    public void searchVideo(String text) {
        helperMethods.sendKeys(searchBox, text);
        clickSearchButton();
        helperMethods.waitUntilPageIsLoaded();
    }

    public void clickSearchButton(){
        helperMethods.submitForm(searchFormSubmit);
    }


}
