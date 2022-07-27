package pages.google;

import base.HelperMethods;
import org.openqa.selenium.By;

public class GooglePage {

    private By searchBox = By.name("q");
    private By searchButton = By.name("btnK");
    private String resultHeader = "//h3[text()='<textReplace>']";

    private HelperMethods helperMethods;

    public GooglePage(HelperMethods helperMethods) {
        this.helperMethods = helperMethods;
    }

    public void search(String text) {
        helperMethods.sendKeys(searchBox, text);
        clickSearchBtn();
        helperMethods.waitUntilPageIsLoaded();
    }

    public void clickSearchBtn() {
        helperMethods.click(searchButton);
    }

    public String getResultHeaderText(String text) {
        By header = By.xpath(resultHeader.replace("<textReplace>", text));
        return helperMethods.getElementText(header);
    }

}
