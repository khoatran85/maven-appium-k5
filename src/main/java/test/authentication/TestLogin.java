package test.authentication;

import Utils.data.DataObjectBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTestEx;
import test_flows.authentication.LoginFlow;

public class TestLogin extends BaseTestEx {
    @Test (dataProvider = "loginData")
    public void loginValidCred(LoginCred loginCred){
        AppiumDriver<MobileElement> appiumDriver = getAndroidDriver();
        LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred);
        loginFlow.login().verifyLogin();
        Assert.fail();
    }

    @DataProvider
    public LoginCred[] loginData(){

        String filePath = "/src/main/resources/test-data/LoginCred.json";
        return DataObjectBuilder.buildJsonDataObject(filePath, LoginCred[].class);
    }


}
