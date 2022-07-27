package base;

import driverFactory.DriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import properties.PropertiesConfig;

public abstract class BaseTest {

    public WebDriver driver;
    public HelperMethods helperMethods;
    protected final PropertiesConfig config = ConfigFactory.create(PropertiesConfig.class);

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        DriverManager.setDriver(config.browser());
        driver = DriverManager.getDriver();
        helperMethods = new HelperMethods(DriverManager.getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        DriverManager.quitDriver();
    }
}
