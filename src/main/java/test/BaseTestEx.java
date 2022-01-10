package test;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import java.util.*;

public class BaseTestEx {
    private AppiumDriver<MobileElement> appiumDriver;
    private DriverFactoryEx driverFactory;
    private final List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private ThreadLocal<DriverFactoryEx> driverThread;
    private String udid;
    private String systemPort;

    @BeforeTest(alwaysRun = true, description = "Init all Appium sessions")
    @Parameters({"udid", "systemPort"})
    public void beforeTest(String udid, String systemPort) {
        this.udid = udid;
        this.systemPort = systemPort;
        deleteScreenShotFiles("Screen Shot");
        deleteScreenShotFiles("allure-report");
        deleteScreenShotFiles("allure-results");
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactoryEx driverThread = new DriverFactoryEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }


    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driverThread.get().quitAppiumSession();
    }

    public AppiumDriver<MobileElement> getAndroidDriver() {
        if (appiumDriver == null) {
            appiumDriver = driverThread.get().getAndroidDriver(udid, systemPort);
        }
        return appiumDriver;
    }

    @AfterMethod(description = "Capture screen shot on failure")
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            //1. Get test method name
            String testMethodName = result.getName();

            //2. Location to save screenshot
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);

            //2022-01-06-07-39-10
            String dateTaken = y + "-" + m + "-" + d + "|" + hr + ":" + min + ":" + sec;

            //3. Location to save
            String fileLocation = System.getProperty("user.dir") + "/Screen Shot/" + testMethodName + "_" + dateTaken + ".png";

            //4. Save
            File screenShot = driverThread.get().getAndroidDriver().getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenShot, new File(fileLocation));
                Path content = Paths.get(fileLocation);
                InputStream inputStream = Files.newInputStream(content);
                Allure.addAttachment(testMethodName, inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        private void deleteScreenShotFiles(String folderName){
            String screenShotFolder = System.getProperty("user.dir").concat("/" + folderName);
            try {
                FileUtils.deleteDirectory(new File(screenShotFolder));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

