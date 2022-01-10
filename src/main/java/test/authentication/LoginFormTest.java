package test.authentication;

import driver.DriverFactory;
import Utils.data.DataObjectBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import models.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.authentication.LoginFlow;

public class LoginFormTest extends BaseTest {

    //    @Test
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
                    .clickOnLoginBtn();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    @Test(dataProvider = "invalidCredData")
    public void loginWithInvalidCred(LoginCred loginCredData) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        LoginPage loginPage = new LoginPage(androidDriver);
        loginPage.bottomNavigationComp().clickOnLoginLbl();

        //Method chaining
        loginPage
                .inputUsername(loginCredData.getEmail())
                .inputPassword(loginCredData.getPassword())
                .clickOnLoginBtn();
    }

    @Test(dataProvider = "invalidCredData")
    public void loginWithInvalidCred01(LoginCred loginCredData){
//        DriverFactory.startAppiumServer();
        AppiumDriver<MobileElement> androidDriver = getAndroidDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver, loginCredData);
        loginFlow.login().verifyLogin();
    }

//    @Test(dataProvider = "ValidCredData")
    public void loginWithValidCred01(LoginCred loginCredData){
//        DriverFactory.startAppiumServer();
        AppiumDriver<MobileElement> androidDriver = getAndroidDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver, loginCredData);
        loginFlow.login().verifyLogin();
    }


    @DataProvider
    public LoginCred[] invalidCredData() {
        String InvalidCredPath = "/src/main/resources/test-data/InvalidLoginCreds.json";
        return DataObjectBuilder.buildJsonDataObject(InvalidCredPath, LoginCred[].class);
    }

    @DataProvider
    public LoginCred[] ValidCredData() {
        String InvalidCredPath = "/src/main/resources/test-data/ValidLoginCreds.json";
        return DataObjectBuilder.buildJsonDataObject(InvalidCredPath, LoginCred[].class);
    }
}
