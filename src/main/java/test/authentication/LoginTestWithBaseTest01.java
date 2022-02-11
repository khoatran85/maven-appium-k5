package test.authentication;

import driver.DriverFactory;
import Utils.data.DataObjectBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTestEx;
import test_flows.authentication.LoginFlow;

public class LoginTestWithBaseTest01 extends BaseTestEx {


    @Test(dataProvider = "invalidCredData")
    public void TC03(LoginCred loginCredData) {
        DriverFactory.startAppiumServer();
        AppiumDriver<MobileElement> androidDriver = getAndroidDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver, loginCredData);
        loginFlow.login().verifyLogin();
    }



    @DataProvider
    public LoginCred[] invalidCredData() {
        String InvalidCredPath = "/src/main/resources/test-data/ValidLoginCreds.json";
        return DataObjectBuilder.buildJsonDataObject(InvalidCredPath, LoginCred[].class);
    }
}