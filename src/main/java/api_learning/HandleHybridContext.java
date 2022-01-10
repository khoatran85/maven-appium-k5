package api_learning;

import driver.DriverFactory;
import contexts.AppContexts;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class HandleHybridContext implements AppContexts {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {
            androidDriver.findElementByAccessibilityId("Webview").click();

            WebDriverWait wait = new WebDriverWait(androidDriver, 10L);
            wait.until(moreThanOneContext(androidDriver));

            androidDriver.getContextHandles().forEach(context ->{
                System.out.println(context);
            });

            androidDriver.context(WEB);

            //Click to navigation bar toggle
            androidDriver.findElementByCssSelector(".navbar__toggle").click();

            Thread.sleep(3000);

            List<MobileElement> menuItems = androidDriver.findElementsByCssSelector(".menu__list-item a");
            List<MenuItem> menuItemList = new ArrayList<>();

            if(menuItems.isEmpty()) {
                throw new RuntimeException("[ERR] Menu items is empty");
            }

            menuItems.forEach(menuItem ->{
                String itemText = menuItem.getText();
                String itemHyperlink = menuItem.getAttribute("href");
                if(StringUtils.isEmpty(itemText))
                    menuItemList.add(new MenuItem("GitHub", itemHyperlink));
                else
                    menuItemList.add(new MenuItem(itemText, itemHyperlink));
            });

            menuItemList.forEach(menuItemData ->{
                System.out.println(menuItemData);
            });

            //Switch back to native context
            androidDriver.context(NATIVE);
            androidDriver.findElementByAccessibilityId("Login").click();

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            androidDriver.quit();
        }

    }

    private static ExpectedCondition<Boolean> moreThanOneContext(AppiumDriver<MobileElement> appiumDriver){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return appiumDriver.getContextHandles().size()>1;
            }
        };
    }

    public static class MenuItem{
        private String text;
        private String hyperLink;

        public MenuItem(String text, String hyperLink) {
            this.text = text;
            this.hyperLink = hyperLink;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "text='" + text + '\'' +
                    ", hyperLink='" + hyperLink + '\'' +
                    '}';
        }
    }
}