package test.authentication;

import Driver.DriverFactory;
import Utils.data.DataObjectBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test.gson.LoginCred;
import test_flows.authentication.LoginFlow;

public class LoginTestWithBaseTest01 extends BaseTest {


//    @Test(dataProvider = "invalidCredData")
    public void TC01(LoginCred LoginCredData){
        DriverFactory.startAppiumServer();
        AppiumDriver<MobileElement> androidDriver = getAndroidDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver,LoginCredData);
        loginFlow.login().verifyLogin();
    }

    @Test
    public void TC02(){
        AppiumDriver<MobileElement> androidDriver = getAndroidDriver();
        LoginPage loginPage = new LoginPage(androidDriver);
        loginPage.bottomNavigationComp().clickOnLoginLbl();
        System.out.println("end tc02");
    }
    @Test
    public void TC03(){
        LoginPage loginPage = new LoginPage(getAndroidDriver());
        loginPage.bottomNavigationComp().clickOnLoginLbl();
        System.out.println("end tc03");

    }

    @DataProvider
    public LoginCred[] invalidCredData() {
        String InvalidCredPath = "/src/main/resources/test-data/InvalidLoginCreds.json";
        return DataObjectBuilder.buildJsonDataObject(InvalidCredPath, LoginCred[].class);
    }

}
