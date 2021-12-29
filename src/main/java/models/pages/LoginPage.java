package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;

public class LoginPage {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By LoginBntSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginPage inputUsername(String username){
        appiumDriver.findElement(usernameSel).sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password){
        appiumDriver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    public void clickOnLoginButton(){
        appiumDriver.findElement(LoginBntSel).click();
    }

    public BottomNavigationComponent bottomNavigationComp(){
        return new BottomNavigationComponent(appiumDriver);
    }

}
