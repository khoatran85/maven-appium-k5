package test;

import Driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginFormTest {

    @Test
    public void testLogin() {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {

            LoginPage loginPage = new LoginPage(androidDriver);
            loginPage.bottomNavigationComp().clickOnLoginLbl();

            //Method chaining
            loginPage
                    .inputUsername("teo@sth.com")
                    .inputPassword("12345678")
                    .clickOnLoginButton();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
