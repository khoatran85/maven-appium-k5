package api_learning;

import Driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Dimension;


import java.time.Duration;


public class Swipe {
    public static void main(String[] args) throws InterruptedException {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

//        androidDriver.findElementByXPath("//*[@content-desc='Forms']").click();
//
//        WebDriver driver;
//        WebDriverWait wait = new WebDriverWait(androidDriver,10L );
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Form components']")));
//
        //Get mobile size
        Dimension windowSize = androidDriver.manage().window().getSize();

        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

//        //Calculate touch point
//        int xStartPoint = (50 * screenWidth) / 100;
//        int xEndPoint = xStartPoint;
//        int yStartPoint = (80 * screenHeight) / 100;
//        int yEndPoint = (10 * screenHeight) / 100;
//
//        PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
//        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
//
//        TouchAction action = new TouchAction(androidDriver);
//
//        //press -> move up -> release ->
//        action
//                .press(startPoint)
//                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
//                .moveTo(endPoint)
//                .release()
//                .perform();
//
//        androidDriver.findElementByAccessibilityId("button-Active").click();

//Calculate touch point
        int xStartPoint = (50 * screenWidth) / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = 0;
        int yEndPoint = (80 * screenHeight) / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        TouchAction action = new TouchAction(androidDriver);

        //press -> move up -> release ->
        action
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(endPoint)
                .release()
                .perform();

        Thread.sleep(3);
        action
                .press(endPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(startPoint)
                .release()
                .perform();

    }


}
