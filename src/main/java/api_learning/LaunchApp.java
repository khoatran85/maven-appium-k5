package api_learning;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class LaunchApp {
    public static void main(String[] args) {
        try {
            AppiumDriver<MobileElement> appiumDriver = null;

            //Specify capacities
        DesiredCapabilities desiredCaps = new DesiredCapabilities();
        desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        desiredCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desiredCaps.setCapability(MobileCapabilityTypeEx.UDID, "emulator-5554");
        desiredCaps.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCaps.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

            //Send the desiredCaps into appium server
            URL appiumServer = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AndroidDriver<MobileElement>(appiumServer,desiredCaps);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //init and create an appium session client <---> appium server

    }
}
