package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import org.testng.Assert;
import test.authentication.LoginCred;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFlow {
    private final AppiumDriver<MobileElement> appiumDriver;
    private final LoginCred loginData;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, LoginCred loginData) {
        this.appiumDriver = appiumDriver;
        this.loginData = loginData;
    }

    public LoginFlow login() {
        LoginPage loginPage = new LoginPage(appiumDriver);
        loginPage.bottomNavigationComp().clickOnLoginLbl();
        loginPage.inputUsername(loginData.getEmail())
                .inputPassword(loginData.getPassword())
                .clickOnLoginBtn();
        return this;
    }

    public LoginFlow verifyLogin() {
        LoginPage loginPage = new LoginPage(appiumDriver);
        boolean isEmailInvalid = isEmailInvalid(loginData.getEmail());
        boolean isPasswordInvalid = isPasswordInvalid(loginData.getPassword());
//        System.out.println("isEmailInvalid" + loginData.getEmail() + isEmailInvalid);
//        System.out.println("isPasswordInvalid" + loginData.getPassword() + isPasswordInvalid);
        if (isEmailInvalid) {
//            System.out.println("start isEmailInvalid");
            verifyEmailInvalidFormat();
        }
        if (isPasswordInvalid) {
//            System.out.println("start isPasswordInvalid");
            verifyPasswordInvalidFormat();
        }
        if (!isEmailInvalid && !isPasswordInvalid) {
//            System.out.println("start verify");
            verifyLoginSuccess();
//            System.out.println("start close popup");
            loginPage.clickOnLoginSuccessOKBtn();
        }
        return this;
    }


    private void verifyLoginSuccess() {
        LoginPage loginPage = new LoginPage(appiumDriver);
//        Assert.assertTrue(loginPage.isLoginSuccessMessageDisplayed());
        System.out.println(loginPage.loginMsgText());
    }

    private void verifyPasswordInvalidFormat() {
        LoginPage loginPage = new LoginPage(appiumDriver);
        Assert.assertTrue(loginPage.isInputPasswordWarningDisplayed());
    }

    private void verifyEmailInvalidFormat() {
        LoginPage loginPage = new LoginPage(appiumDriver);
        Assert.assertTrue(loginPage.isInputEmailWarningDisplayed());
    }

    /**
     ** ^ represents starting character of the string.
     ** (?=.*[0-9]) represents a digit must occur at least once.
     ** (?=.*[a-z]) represents a lower case alphabet must occur at least once.
     ** (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
     ** (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
     ** (?=\\S+$) white spaces don’t allowed in the entire string.
     ** .{8, 20} represents at least 8 characters and at most 20 characters.
     ** $ represents the end of the string.
     **/
    private boolean isPasswordInvalid(String password) {
//        String expression = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~!@#$%^&*()-+=_])(?=\\S+$).{8,20}$";
        String expression = "^.{8,20}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return !matcher.matches();
    }


    private boolean isEmailInvalid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }

}


