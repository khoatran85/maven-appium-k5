package test.authentication;

import driver.DriverFactory;
import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import test.BaseTestEx;

import java.util.List;

public class SwipeVerticallyUntil extends BaseTestEx {

    @Test
        public void TC01(){
        AppiumDriver<MobileElement> androidDriver = getAndroidDriver();

        androidDriver.findElementByAccessibilityId("Swipe").click();


        WebDriverWait wait = new WebDriverWait(androidDriver,10L );
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Swipe horizontal']")));

//        Get mobile size
        Dimension windowSize = androidDriver.manage().window().getSize();

        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        //Calculate touch point
        int xStartPoint = (50 * screenWidth) / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = (50 * screenHeight) / 100;
        int yEndPoint = (10 * screenHeight) / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        TouchAction action = new TouchAction(androidDriver);

        //press -> move up -> release ->
        final int MAX_SWIPE_TIME = 10;
        int swipeTime = 0;
        while (swipeTime < MAX_SWIPE_TIME){
            List<MobileElement> matchedElems = androidDriver.findElementsByXPath("//*[@text='You found me!!!']");
            if(!matchedElems.isEmpty()) break;

            action
                    .longPress(startPoint)
                    .moveTo(endPoint)
                    .release()
                    .perform();
            swipeTime ++;
        }

        if(swipeTime == 10){
            throw new RuntimeException("Icon not found!");
        }

    }
}
