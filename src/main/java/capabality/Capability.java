package capabality;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Capability {


    public static DesiredCapabilities IOS = getIOSCapabilities();

    public static DesiredCapabilities ANDROID = getAndroidCapabilities();


    private static DesiredCapabilities getIOSCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("app","/Users/rupin.nath/Library/Developer/Xcode/DerivedData/TestApplication-ftdbhicxakxovogvddqjewvybrac/Build/Products/Debug-iphonesimulator/TestApplication.app");
        capabilities.setCapability("automationName","XCUITest");
        capabilities.setCapability("deviceName","iPhone XR");
        return capabilities;
    }

    private static DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "gmbh.ambidexter.testapplication");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        return capabilities;
    }
}
