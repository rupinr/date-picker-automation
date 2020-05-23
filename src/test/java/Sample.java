import config.Config;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import page.datepicker.DatePicker;
import resolver.OS;
import resolver.PageResolver;
import resolver.Version;

public class Sample {

    private AppiumDriverLocalService service;

    private AndroidDriver<WebElement> driver;


    @Before
    public void setUp() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "gmbh.ambidexter.testapplication");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        driver = new AndroidDriver<WebElement>(service.getUrl(), capabilities);
    }

    @Test
    public void test() {
        Config config = new Config(OS.ANDROID, Version.SDK29);
        DatePicker picker = PageResolver.createMatchingPage(DatePicker.class,driver, config);
        picker.selectDate("12/1/2019");
    }





    @After
    public void clean() {
        service.stop();
    }
}
