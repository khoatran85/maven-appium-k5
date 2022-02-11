package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBntSel = MobileBy.AccessibilityId("button-LOGIN");
    private static final By inputEmailWarningSel = MobileBy.xpath("//*[@text='Please enter a valid email address']");
    private static final By inputPasswordWarningSel = MobileBy.xpath("//*[@text='Please enter at least 8 characters']");

    private static final By loginSuccessPopupSel = MobileBy.xpath("//*[@text='Success']");
//    private static final By loginSuccessOKBtnSel = MobileBy.xpath("//*[@text='OK']");

    @AndroidFindBy(id = "android:id/alertTitle")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'Success'")
    private MobileElement msgTitleElem;

    @AndroidFindBy(id = "android:id/button1")
    @iOSXCUITFindBy(iOSNsPredicate = "label == 'OK'")
    private MobileElement loginSuccessOKBtnElem;


    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(10)),this);
    }

    public void waitForElementVisibility(By by){
        WebDriverWait wait = new WebDriverWait(appiumDriver, 5L);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public LoginPage inputUsername(String username){
        appiumDriver.findElement(usernameSel).clear();
        appiumDriver.findElement(usernameSel).sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password){
        appiumDriver.findElement(passwordSel).clear();
        appiumDriver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    public void clickOnLoginBtn(){
        swipeToLoginBtn();
        appiumDriver.findElement(loginBntSel).click();
    }

    public boolean isLoginSuccessMessageDisplayed(){
        waitForElementVisibility(loginSuccessPopupSel);
        return appiumDriver.findElement(loginSuccessPopupSel).isDisplayed();
    }

    public boolean isInputEmailWarningDisplayed(){
        return appiumDriver.findElement(inputEmailWarningSel).isDisplayed();
    }

    public boolean isInputPasswordWarningDisplayed(){
        return appiumDriver.findElement(inputPasswordWarningSel).isDisplayed();
    }

    public BottomNavigationComponent bottomNavigationComp(){
        return new BottomNavigationComponent(appiumDriver);
    }

    public void clickOnLoginSuccessOKBtn(){
        WebDriverWait wait = new WebDriverWait(appiumDriver, 3L);
//        wait.until(ExpectedConditions.presenceOfElementLocated(loginSuccessOKBtnSel));
//        appiumDriver.findElement(loginSuccessOKBtnSel).click();
        wait.until(ExpectedConditions.visibilityOf(loginSuccessOKBtnElem));
        loginSuccessOKBtnElem.click();
    }

    public void swipeToLoginBtn(){
        WebDriverWait wait = new WebDriverWait(appiumDriver,10L );
//        Get mobile size
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        //Calculate touch point
        int xStartPoint = (50 * screenWidth) / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = (70 * screenHeight) / 100;
        int yEndPoint = (10 * screenHeight) / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        TouchAction action = new TouchAction(appiumDriver);

        //press -> move up -> release ->
        final int MAX_SWIPE_TIME = 5;
        int swipeTime = 0;
        while (swipeTime < MAX_SWIPE_TIME){
            List<MobileElement> matchedElems = appiumDriver.findElements(loginBntSel);
            if(!matchedElems.isEmpty()) break;

            action
                    .longPress(startPoint)
                    .moveTo(endPoint)
                    .release()
                    .perform();
            swipeTime ++;
        }

        if(swipeTime == MAX_SWIPE_TIME){
            throw new RuntimeException("Button not found!");
        }

    }

    public String loginMsgText(){
        return msgTitleElem.getText();
    }
}
