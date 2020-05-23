import capabality.Capability;
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
    }

    @Test
    public void test() {
        driver = new AndroidDriver<WebElement>(service.getUrl(), Capability.ANDROID);
        Config config = new Config(OS.ANDROID, Version.SDK29);
        DatePicker picker = PageResolver.createMatchingPage(DatePicker.class,driver, config);
        picker.selectDate("12/12/2025");
    }

    @Test
    public void test2() {
        driver = new AndroidDriver<WebElement>(service.getUrl(), Capability.IOS);
        Config config = new Config(OS.IOS, Version.IOS11);
        DatePicker picker = PageResolver.createMatchingPage(DatePicker.class,driver, config);
        picker.selectDate("01/06/2020");
    }
    
    @After
    public void clean() {
        service.stop();
    }
}
