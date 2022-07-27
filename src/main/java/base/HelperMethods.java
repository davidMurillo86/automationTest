package base;

import org.aeonbits.owner.ConfigCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import properties.PropertiesConfig;
import java.time.Duration;
import java.util.List;

public class HelperMethods {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(HelperMethods.class);
    private static final PropertiesConfig config = ConfigCache.getOrCreate(PropertiesConfig.class);

    public HelperMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(config.explicitWait()));
    }

    public void waitUntilPageIsLoaded() {
        logger.info("Waiting for page to load");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public boolean waitUntilElementIsVisible(By element) {
        try {
            logger.info("Waiting for element to be visible");
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element)).size() > 0;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean waitUntilElementIsVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(element)).size() > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendKeys(By element, String text) {
        try {

            if (waitUntilElementIsVisible(element)) {
                WebElement input = driver.findElement(element);
                logger.info("Sending text to element: {}", input.getAttribute("outerHTML")
                        .replaceAll("[\n\t]", ""));
                input.clear();
                input.sendKeys(text.trim());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void click(By element) {
        try {
            if (waitUntilElementIsVisible(element)) {
                WebElement clickableElement = driver.findElement(element);
                logger.info("Clicking element: {}", clickableElement.getAttribute("outerHTML")
                        .replaceAll("[\n\t]", ""));
                Actions actions = new Actions(driver);
                actions.moveToElement(clickableElement).click().perform();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void click(WebElement element) {
        try {
            if (waitUntilElementIsVisible(element)) {
                logger.info("Clicking element: {}", element.getAttribute("outerHTML")
                        .replaceAll("[\n\t]", ""));
                Actions actions = new Actions(driver);
                actions.moveToElement(element).click().perform();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void submitForm(By element) {
        try {
            if (waitUntilElementIsVisible(element)) {
                WebElement form = driver.findElement(element);
                logger.info("Submitting form with element: {}", form.getAttribute("outerHTML")
                        .replaceAll("[\n\t]", ""));
                form.submit();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getElementText(By element) {
        String elementText = "";
        try {
            if (waitUntilElementIsVisible(element)) {
                WebElement elementWithText = driver.findElement(element);
                logger.info("Getting text from element: {}", elementWithText.getAttribute("outerHTML")
                        .replaceAll("[\n\t]", ""));
                elementText = elementWithText.getText().trim();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return elementText;
    }

    public void scrollToElement(By element) {
        try {
            WebElement elementToScrollTo = driver.findElement(element);
            logger.info("Scrolling to element: {}", elementToScrollTo.getAttribute("outerHTML")
                    .replaceAll("[\n\t]", ""));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", elementToScrollTo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void scrollToElement(WebElement elementToScrollTo) {
        try {
            logger.info("Scrolling to element: {}", elementToScrollTo.getAttribute("outerHTML")
                    .replaceAll("[\n\t]", ""));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", elementToScrollTo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void select(By selectElement, String option) {
        try {
            WebElement selectWebElement = driver.findElement(selectElement);
            logger.info("Selecting option '{}' from element: {}", option,
                    selectWebElement.getAttribute("outerHTML").replaceAll("[\n\t]", ""));
            Select select = new Select(selectWebElement);
            select.selectByVisibleText(option);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getSelectedOption(By selectElement) {
        try {
            WebElement selectWebElement = driver.findElement(selectElement);
            logger.info("Getting selected option from element: {}", selectWebElement.getAttribute("outerHTML")
                    .replaceAll("[\n\t]", ""));
            Select select = new Select(selectWebElement);
            return select.getFirstSelectedOption().getText();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getElementAttribute(By element, String attribute) {
        String elementAttribute = "";
        try {
            if (waitUntilElementIsVisible(element)) {
                WebElement elementAttributeWebElement = driver.findElement(element);
                logger.info("Getting attribute '{}' from element: {}", attribute, elementAttributeWebElement.getAttribute("outerHTML")
                        .replaceAll("[\n\t]", ""));
                elementAttribute = elementAttributeWebElement.getAttribute(attribute);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return elementAttribute;
    }

    public void navigateToURL(String url) {
        try {
            logger.info("Navigating to URL: {}", url);
            driver.navigate().to(url);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean isElementPresent(By element) {
        try {
            logger.info("Checking if element is present");
            return driver.findElements(element).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getElements(By element) {
        try {
            if (waitUntilElementIsVisible(element)) {
                return driver.findElements(element);
            } else
                return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkVisibilityOfElement(By element) {
        try {
            logger.info("Checking visibility of element");
            Wait<WebDriver> fluentWait = new FluentWait<>(this.driver)
                    .withTimeout(Duration.ofSeconds(config.explicitWait()))
                    .pollingEvery(Duration.ofSeconds(5))
                    .ignoring(NoSuchElementException.class);

            return fluentWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element)).size() > 0;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
