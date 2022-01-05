package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import test.gson.LoginCred;

public class LoginFlow {
    private final AppiumDriver<MobileElement> appiumDriver;
    private LoginCred loginCredData;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, LoginCred loginCredData) {
        this.appiumDriver = appiumDriver;
        this.loginCredData = loginCredData;
    }

    public LoginFlow login(){

        LoginPage loginPage = new LoginPage(appiumDriver);
        loginPage.bottomNavigationComp().clickOnLoginLbl();

        //Method chaining
        loginPage
                .inputUsername(loginCredData.getUsername())
                .inputPassword(loginCredData.getPassword())
                .clickOnLoginButton();
        return this;
    }

    public void verifyLogin(){
        boolean isEmailInvalid = isEmailInvalid(loginCredData.getUsername());
        boolean isPasswordInvalid = isPasswordInvalid(loginCredData.getPassword());
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
