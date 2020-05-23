import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.List;

public class Sample {

    private static AppiumDriverLocalService service;


    @Test
    public void test() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        System.out.println(service.getUrl());

        File app = new File("/Users/rupin.nath/Downloads/QA-Challenge-master/TestApplicationQA-Android-master/app/build/outputs/apk/debug/app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "gmbh.ambidexter.testapplication");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(service.getUrl(), capabilities);
        driver.findElementById("android:id/date_picker_header_year").click();

        MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiSelector().resourceId(\"android:id/date_picker_year_picker\")"));


        System.out.println(element.getCenter().x +" "+element.getCenter().y);
        new TouchAction(driver)
                .press(PointOption.point(element.getCenter().x,element.getCenter().y+200))
                .moveTo(PointOption.point(element.getCenter().x,element.getCenter().y))
                .release()
                .perform();



//        (new TouchAction(driver))
//                .press(PointOption.point(element.getCenter().x,element.getCenter().y))
//                .moveTo(PointOption.point(element.getCenter().x,element.getCenter().y+10))
//                .release()
//                .perform();
//        (new TouchAction(driver))
//                .press(PointOption.point(element.getCenter().x,element.getCenter().y))
//                .moveTo(PointOption.point(element.getCenter().x,element.getCenter().y+10))
//                .release()
//                .perform();
//        (new TouchAction(driver))
//                .press(PointOption.point(element.getCenter().x,element.getCenter().y))
//                .moveTo(PointOption.point(element.getCenter().x,element.getCenter().y+10))
//                .release()
//                .perform();
//        (new TouchAction(driver))
//                .press(PointOption.point(element.getCenter().x,element.getCenter().y))
//                .moveTo(PointOption.point(element.getCenter().x,element.getCenter().y+10))
//                .release()
//                .perform();
//        (new TouchAction(driver))
//                .press(PointOption.point(element.getCenter().x,element.getCenter().y))
//                .moveTo(PointOption.point(element.getCenter().x,element.getCenter().y+10))
//                .release()
//                .perform();
//        (new TouchAction(driver))
//                .press(PointOption.point(element.getCenter().x,element.getCenter().y))
//                .moveTo(PointOption.point(element.getCenter().x,element.getCenter().y+10))
//                .release()
//                .perform();

//        driver.findElement(MobileBy.AndroidUIAutomator(
//                "new UiScrollable( new UiSelector().resourceId(\"android:id/date_picker_year_picker\")).flingForward()"));



    }




    @After
    public void clean() {
        service.stop();
    }
}
