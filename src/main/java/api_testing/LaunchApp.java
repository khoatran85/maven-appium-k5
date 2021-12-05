package api_testing;

import caps.MobileCapabilityTypeEx;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LaunchApp {
    public static void main(String[] args) {

        //Specify capacities

        DesiredCapabilities desiredCaps = new DesiredCapabilities();
        desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        desiredCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desiredCaps.setCapability(MobileCapabilityTypeEx.UDID, "");
        desiredCaps.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "");
        desiredCaps.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "");

        //init and create an appium session client <---> appium server


        



    }
}
