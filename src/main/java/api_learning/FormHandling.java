package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FormHandling {
    public static void main(String[] args) throws InterruptedException {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        androidDriver.findElementByAccessibilityId("Login").click();

        androidDriver.findElementByAccessibilityId("input-email").sendKeys("khoa@gmail.com");

        androidDriver.findElementByAccessibilityId("input-password").sendKeys("12345678");

        androidDriver.findElementByAccessibilityId("button-LOGIN").click();

        Thread.sleep(3000);

        WebDriver driver;
        WebDriverWait wait = new WebDriverWait(androidDriver, 10l);
        wait.until(ExpectedConditions.visibilityOf(androidDriver.findElement(By.id("android:id/alertTitle"))));

        System.out.println(androidDriver.findElement(By.id("android:id/alertTitle")).getText());
        System.out.println(androidDriver.findElement(By.id("android:id/message")).getText());

//        androidDriver.findElementByAccessibilityId("android:id/button1").click();
    }
}
