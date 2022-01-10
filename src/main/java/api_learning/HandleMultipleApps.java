package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HandleMultipleApps {

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, 10L);

            // go to login form
            androidDriver.findElementByAccessibilityId("Login").click();


            // input correct creds then login
            androidDriver.findElementByAccessibilityId("input-email").sendKeys("khoa@gmail.com");
            androidDriver.findElementByAccessibilityId("input-password").sendKeys("12345678");


            // put app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));

            // open settings -> handle wifi...
            androidDriver.activateApp("com.android.settings");
            androidDriver.findElementByXPath("//*[@text='Network & internet']").click();
            MobileElement wifiSwitchBtnElem = androidDriver.findElement(By.id("com.android.settings:id/switchWidget"));
            boolean isWifiOn = wifiSwitchBtnElem.getText().equals("ON");

            wifiSwitchBtnElem.click();
            if(isWifiOn){
                wifiSwitchBtnElem.click();
            }

            androidDriver.activateApp("com.wdiodemoapp");


            androidDriver.findElementByAccessibilityId("button-LOGIN").click();

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            androidDriver.quit();
        }

    }
    // open test app again
}

