package driverFactory;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import properties.PropertiesConfig;

public class OptionsManager {
    private static final PropertiesConfig config = ConfigFactory.create(PropertiesConfig.class);

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments(config.chromeResolution());
        options.setHeadless(config.headless());
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(config.headless());
        firefoxOptions.addArguments(config.firefoxResolutionHeight());
        firefoxOptions.addArguments(config.firefoxResolutionWidth());
        return firefoxOptions;
    }

}
