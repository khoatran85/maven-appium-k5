package driver;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class DriverFactoryEx {

    private AppiumDriver<MobileElement> appiumDriver;

    public AppiumDriver<MobileElement> getAndroidDriver(String udid, String systemPort, String platformName, String platformVersion) {
        initDriver(udid, systemPort, platformName, platformVersion);
        return appiumDriver;
    }

    public AppiumDriver<MobileElement> getAndroidDriver() {
        return appiumDriver;
    }

    private void initDriver(String udid, String systemPort, String platformName, String platformVersion) {
        try {
            PlatformType.valueOf(platformName.toLowerCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("We don't support " + platformName);
        }

        boolean isAndroid = platformName.equalsIgnoreCase("android");

        try {
            DesiredCapabilities desiredCaps = new DesiredCapabilities();
            desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, platformName);

            if(isAndroid) {
                desiredCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
                desiredCaps.setCapability(MobileCapabilityTypeEx.UDID, udid);
                desiredCaps.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, Integer.valueOf(systemPort));
                desiredCaps.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
                desiredCaps.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
            }else{
                desiredCaps.setCapability(MobileCapabilityTypeEx.WDA_LOCAL_PORT, Integer.valueOf(systemPort));
                desiredCaps.setCapability(MobileCapabilityTypeEx.DEVICE_NAME, udid);
                desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_VERSION, platformVersion);
                desiredCaps.setCapability(MobileCapabilityTypeEx.BUNDLE_ID, "org.wdioNativeDemoApp");
                desiredCaps.setCapability(MobileCapabilityTypeEx.NO_RESET, true);
            }

            URL remoteServer = new URL("http://localhost:4723/wd/hub");
            String hub = System.getProperty("hub") != null ? System.getProperty("hub") : System.getenv("hub");
            if (hub != null)
                remoteServer = new URL(hub.concat(":4444/wd/hub"));

            appiumDriver = new AndroidDriver<>(remoteServer, desiredCaps);
            appiumDriver.manage().timeouts().implicitlyWait(3l, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void quitAppiumSession() {
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}
