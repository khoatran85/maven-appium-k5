package test3;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;
import test.BaseTestEx;


public class testB extends BaseTestEx {
        AppiumDriver<MobileElement> driver = getAndroidDriver();

    @Test
        public void testB(){
            System.out.println(driver);
        }
    }

