package Driver;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverFactory {
    private static AppiumDriverLocalService appiumServer;
    private static AndroidDriver<MobileElement> androidDriver;

    public static void startAppiumServer() {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
//        appiumServiceBuilder.withIPAddress("127.0.0.1").usingPort(4723);
        appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();
    }

    public static void stopAppiumServer() {
//        appiumServer.stop();
        String killNodeWindowsCommand = "staskill /F /IM node.exe";
        String killNodeLinuxCommand = "killall node";
        String killNodeCmd = System.getProperty("os.name").toLowerCase().startsWith("windows")
                ? killNodeWindowsCommand : killNodeLinuxCommand;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(killNodeCmd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static AndroidDriver<MobileElement> getAndroidDriver() {
        initAndroidDriver();
        return androidDriver;
    }

    private static void initAndroidDriver() {
        DesiredCapabilities desiredCaps = new DesiredCapabilities();
        desiredCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        desiredCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desiredCaps.setCapability(MobileCapabilityTypeEx.UDID, "emulator-5554");
        desiredCaps.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCaps.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

        //Send the desiredCaps into appium server
        androidDriver = new AndroidDriver<MobileElement>(appiumServer.getUrl(), desiredCaps);
    }
}
