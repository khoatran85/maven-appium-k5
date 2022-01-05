package test.excel;

import Driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.gson.LoginCred;

import java.io.File;

public class ReadExcelTest {
    @Test(dataProvider = "loginData")
    public void readExcel(String username, String password){
        System.out.println(username);
        System.out.println(password);
    }

    @Test(dataProvider = "loginData")
    public void loginWithInvalidCred01(String username, String password){
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        LoginCred loginData = new LoginCred(username, password);
        LoginFlowExcel loginFlow = new LoginFlowExcel(androidDriver, loginData);
        loginFlow.login().verifyLogin();
    }


    @DataProvider
    public Object[][] loginData() {

        File excelFileLocation = new File(System.getProperty("user.dir").concat("/src/main/resources/test-data/ValidCredExcel.xlsx"));
        String sheetName = "Sheet1";
        int startRowIndex = 1;
        int startColumnIndex = 0;
        ExcelReaderUtil excelReaderUtil = new ExcelReaderUtil(excelFileLocation, sheetName, startRowIndex, startColumnIndex);
        int totalRow = excelReaderUtil.getTotalRow();
        int totalColumn = excelReaderUtil.getTotalColumn();

        Object[][] loginData = new Object[totalRow - startRowIndex][totalColumn - startColumnIndex];
        for (int startRow = startRowIndex; startRow < totalRow; startRow++) {
            for (int startColumn = startColumnIndex; startColumn < totalColumn; startColumn++) {
                loginData[startRow - startRowIndex][startColumn] = excelReaderUtil.getCellValue(startRow, startColumn);
            }
        }

        return loginData;
    }
}
