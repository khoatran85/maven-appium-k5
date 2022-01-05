package test;

import Driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    private DriverFactoryEx driverFactory;
    private AppiumDriver<MobileElement> appiumDriver;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        driverFactory = new DriverFactoryEx();
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driverFactory.quitAppiumSession();
    }

    protected AppiumDriver<MobileElement> getAndroidDriver() {
        if (appiumDriver == null) {
            appiumDriver = driverFactory.getAndroidDriver();
        }
        return appiumDriver;
    }
}
