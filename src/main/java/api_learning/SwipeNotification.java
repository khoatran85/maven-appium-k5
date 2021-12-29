package api_learning;

import Driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import javax.management.Notification;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SwipeNotification {
    public static void main(String[] args) throws InterruptedException {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        Dimension windowSize = androidDriver.manage().window().getSize();

        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        //Calculate touch point
        int xStartPoint = (50 * screenWidth) / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = 0;
        int yEndPoint = (95 * screenHeight) / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        TouchAction action = new TouchAction(androidDriver);

        //press -> move up -> release ->
        action
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(endPoint)
                .release()
                .perform();

        List<MobileElement> notificationElems = androidDriver.findElements(MobileBy.id("android:id/notification_main_column"));
        if(notificationElems.isEmpty()){
            throw new RuntimeException("Notification list is empty");
        }

        List<Notification> notifications = new ArrayList<>();

        //Functional Interface + lamda expression
        //Anonymous function | return Type methodName(){} | () -> {}
        notificationElems.forEach(notificationElem -> {
            String notificationTitle = notificationElem.findElement(By.id("android:id/title")).getText();
            By bigTextById = MobileBy.id("android:id/big_text");
            By textById = MobileBy.id("android:id/text");

            List<MobileElement> bigTextElems = androidDriver.findElements(bigTextById);
            List<MobileElement> textElems = androidDriver.findElements(textById);

            List<MobileElement> notificationBodyElems = !bigTextElems.isEmpty() ? bigTextElems : textElems;
            String notificationBody = notificationBodyElems.isEmpty() ? null : notificationBodyElems.get(0).getText();
            notifications.add(new Notification(notificationTitle, notificationBody));
        });

        //verification
        notifications.forEach(notification -> {
            System.out.println(notification);
        });


    }

    public static class Notification{
        private final String title;
        private final String content;

        public Notification(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "Notification{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}