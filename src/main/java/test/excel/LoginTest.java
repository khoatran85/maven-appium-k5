package test.authentication;

import Driver.DriverFactory;
import Utils.data.DataObjectBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test.excel.ConvertExcel2Json;
import test.gson.LoginCred;
import test_flows.authentication.LoginFlow;

import java.util.List;

public class LoginTest extends BaseTest {


    @Test(dataProvider = "invalidCredData")
    public void TC01(LoginCred LoginCredData){
        DriverFactory.startAppiumServer();
        AppiumDriver<MobileElement> androidDriver = getAndroidDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver,LoginCredData);
        loginFlow.login().verifyLogin();
    }



    @DataProvider
    public LoginCred[] invalidCredData() {
        String validCredPath = "src/main/resources/test-data/ValidCredExcel.xlsx";

        // Step 1: Read Excel File into Java List Objects
         return ConvertExcel2Json.readExcelFile(validCredPath);

    }

}
