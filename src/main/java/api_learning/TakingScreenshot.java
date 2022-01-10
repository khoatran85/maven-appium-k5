package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class TakingScreenshot {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            androidDriver.findElementByAccessibilityId("Login").click();

            WebDriverWait wait = new WebDriverWait(androidDriver,10L);
            WebElement loginBtnElem = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("button-LOGIN")));

            // Taking screenshot | Whole screen
            File base64ScreenshotData = androidDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginForm.png");
            FileUtils.copyFile(base64ScreenshotData,new File(fileLocation));


            // Taking screenshot | on an element
            File base64LoginBtnData = loginBtnElem.getScreenshotAs(OutputType.FILE);
            String loginBtnFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginButton.png");
            FileUtils.copyFile(base64LoginBtnData,new File(loginBtnFileLocation));


            //Taking screenshot | app screenshot
            MobileElement loginScreen = androidDriver.findElement(MobileBy.AccessibilityId("Login-screen"));
            File base64HomeScreenData = loginScreen.getScreenshotAs(OutputType.FILE);
            String homeScreenFileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("HomeScreen.png");
            FileUtils.copyFile(base64HomeScreenData,new File(homeScreenFileLocation));


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
