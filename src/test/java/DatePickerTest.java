import config.Config;
import config.ConfigurationHolder;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import page.datepicker.DatePicker;
import resolver.PageResolver;

public class DatePickerTest {

    private AppiumDriverLocalService service;

    private AndroidDriver<WebElement> driver;

    private final ConfigurationHolder configurationHolder = ConfigurationHolder.INSTANCE;

    private Config config;


    @Before
    public void setUp() {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        driver = new AndroidDriver<>(service.getUrl(), configurationHolder.desiredCapabilities);
        config = configurationHolder.config;
    }

    @Test
    public void selectDateTest() {
        DatePicker picker = PageResolver.createMatchingPage(DatePicker.class,driver, config);
        picker.selectDate("30/06/2020");
        Assert.assertEquals(picker.getSelectedDate(), "Tue, Jun 30");
    }

    @After
    public void clean() {
        service.stop();
    }
}
