package test.excel;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import test.gson.LoginCred;

public class LoginFlowExcel {
    private final AppiumDriver<MobileElement> appiumDriver;
//    private String username;
//    private String password;
    LoginCred loginCred;

    public LoginFlowExcel(AppiumDriver<MobileElement> appiumDriver, LoginCred loginCred) {
        this.appiumDriver = appiumDriver;
//        this.username = username;
//        this.password = password;
        this.loginCred = loginCred;
    }

    public LoginFlowExcel login(){

        LoginPage loginPage = new LoginPage(appiumDriver);
        loginPage.bottomNavigationComp().clickOnLoginLbl();

        //Method chaining
        loginPage
                .inputUsername(loginCred.getUsername())
                .inputPassword(loginCred.getPassword())
                .clickOnLoginButton();
        return this;
    }

    public void verifyLogin(){
        boolean isEmailInvalid = isEmailInvalid(loginCred.getUsername());
        boolean isPasswordInvalid = isPasswordInvalid(loginCred.getPassword());
    if(isEmailInvalid)
        verifyInvalidEmailFormat();
    if(isPasswordInvalid)
        verifyInvalidPasswordFormat();
    if(!isEmailInvalid && !isPasswordInvalid)
        verifyLoginSuccess();
    }

    private void verifyLoginSuccess() {
    }

    private void verifyInvalidPasswordFormat() {
    }

    private boolean isPasswordInvalid(String password) {
        return true;
    }

    private boolean isEmailInvalid(String username) {
        return false;
    }

    private void verifyInvalidEmailFormat() {
    }

}
