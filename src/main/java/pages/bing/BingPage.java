package pages.bing;

import base.HelperMethods;
import org.openqa.selenium.By;
import org.testng.Assert;

public class BingPage {

    private By searchBox = By.name("q");
    private By submitSearchForm = By.id("sb_form");
    private String resultHeader = "//h2/a[text()='<textReplace>']";

    private HelperMethods helperMethods;

    public BingPage(HelperMethods helperMethods){
        this.helperMethods = helperMethods;
    }

    public void search(String text) {
        helperMethods.sendKeys(searchBox, text);
        submitSearch();
    }
    public void submitSearch() {
        helperMethods.submitForm(submitSearchForm);
        helperMethods.waitUntilPageIsLoaded();
    }

    public String getResultHeaderText(String text) {
        By header = By.xpath(resultHeader.replace("<textReplace>", text));
        return helperMethods.getElementText(header);
    }
}
