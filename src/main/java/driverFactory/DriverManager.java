package driverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import properties.PropertiesConfig;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final PropertiesConfig config = ConfigFactory.create(PropertiesConfig.class);

    public synchronized static void setDriver(String browser) {
        switch (browser) {
            case "chrome":
                driver.set(WebDriverManager.chromedriver().forceDownload().capabilities(OptionsManager.getChromeOptions()).create());
                break;
            case "firefox":
                driver.set(WebDriverManager.firefoxdriver().capabilities(OptionsManager.getFirefoxOptions()).create());
                break;
            default:
                throw new RuntimeException(String.format("Browser %s is not configured", browser));
        }
        driver.get().manage().timeouts().pageLoadTimeout(Duration.of(config.explicitWait(), ChronoUnit.SECONDS));
    }

    public synchronized static WebDriver getDriver() {
        return driver.get();
    }

    public synchronized static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}
