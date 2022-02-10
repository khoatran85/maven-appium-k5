package test;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    private AppiumDriver<MobileElement> appiumDriver;
    private DriverFactoryEx driverFactory;
    private String udid;
    private String systemPort;
    @BeforeClass(alwaysRun = true)
    public void beforeTest() {
        driverFactory = new DriverFactoryEx();
    }

    @AfterClass(alwaysRun = true)
    public void afterTest() {
        driverFactory.quitAppiumSession();
    }

    public AppiumDriver<MobileElement> getAndroidDriver() {
        if (appiumDriver == null) {
            appiumDriver = getAndroidDriver();
        }
        return appiumDriver;
    }
}
