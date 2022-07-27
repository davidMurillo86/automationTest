package listeners;

import driverFactory.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {

    private static final String sp = File.separator;
    private final String filePath = System.getProperty("user.dir") + sp + "target" + sp + "screenshots";
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("{} test has failed", result.getName());
        logger.error(result.getThrowable());
        String methodName = result.getName().trim();
        WebDriver driver = DriverManager.getDriver();
        takeScreenShot(methodName, driver);
    }

    public void takeScreenShot(String methodName, WebDriver driver) {
        logger.info("Taking screenshot");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.createDir(new File(filePath));
            FileHandler.copy(scrFile, new File(filePath + sp + methodName + ".png"));
            logger.info("Placed screenshot in: {}", filePath);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        logger.info("Testing has finished");
    }

    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("{} test has passed", result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("{} test has been skipped", result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
        logger.info("Starting Automation Test Framework");
    }
}
