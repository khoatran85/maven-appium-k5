package api_learning;

import Driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;

public class PutAppIntoBackground {
    public static void main(String[] args) throws InterruptedException {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        androidDriver.findElementByAccessibilityId("Login").click();

        //put app into background
        androidDriver.runAppInBackground(Duration.ofSeconds(3));

        // run in background until user open app again
        androidDriver.runAppInBackground(Duration.ofSeconds(-1));


    }

}
