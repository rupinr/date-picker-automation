package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;

public class AppiumUtil {

    public static void swipeDown(AppiumDriver<MobileElement> appiumDriver, Point point, int swipeDistance) {
        new TouchAction(appiumDriver)
                .press(PointOption.point(point.x, point.y))
                .moveTo(PointOption.point(point.x,point.y+swipeDistance))
                .release()
                .perform();
    }

    public static void swipeUp(AppiumDriver<MobileElement> appiumDriver, Point point, int swipeDistance) {
        System.out.println(point);
                new TouchAction(appiumDriver)
                .press(PointOption.point(point.x, point.y+swipeDistance))
                .moveTo(PointOption.point(point.x,point.y))
                .release()
                .perform();
    }
}
