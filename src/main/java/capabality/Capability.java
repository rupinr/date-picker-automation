package capabality;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Capability {

    public static DesiredCapabilities getIOSCapabilities(String appPath, String automationName, String deviceName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("app",appPath);
        capabilities.setCapability("automationName",automationName);
        capabilities.setCapability("deviceName",deviceName);
        return capabilities;
    }

    public static DesiredCapabilities getAndroidCapabilities(String appPackage, String mainActivity, String apkPath,String deviceName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", mainActivity);
        capabilities.setCapability("app", apkPath);
        capabilities.setCapability("deviceName", deviceName);
        return capabilities;
    }
}
