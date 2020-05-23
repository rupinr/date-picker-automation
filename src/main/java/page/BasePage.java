package page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class BasePage {

    private AppiumDriver<MobileElement> appiumDriver;

    public BasePage(AppiumDriver<MobileElement> appiumDriver){
            this.appiumDriver = appiumDriver;
    }
}
