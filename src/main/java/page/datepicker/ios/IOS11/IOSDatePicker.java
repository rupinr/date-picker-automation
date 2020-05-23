package page.datepicker.ios.IOS11;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import page.BasePage;
import page.datepicker.DatePicker;
import util.AppiumUtil;
import util.DateUtil;

import java.util.Calendar;

public class IOSDatePicker extends BasePage implements DatePicker {

    private AppiumDriver<MobileElement> appiumDriver;

    public IOSDatePicker(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }

    @Override
    public void selectDate(String date) {
        DateUtil.parseInputDate(date);
        Calendar requiredDate = Calendar.getInstance();
        requiredDate.setTime(DateUtil.parseInputDate(date));
        Calendar today = Calendar.getInstance();
        final int swipeDistance = 40;
        long daysInBetween = DateUtil.daysBetween(today, requiredDate);
        if (daysInBetween > 0) {
            for (int i=0; i<=daysInBetween; i++) {
                AppiumUtil.swipeDown(appiumDriver, getPickerWheel().getCenter(), swipeDistance);
            }

        } else if(daysInBetween < 0) {
            for (int i=0; i<=Math.abs(daysInBetween); i++) {
                AppiumUtil.swipeUp(appiumDriver, getPickerWheel().getCenter(), swipeDistance);
            }
        }
        getPickerWheel().click();
    }

    @Override
    public String getSelectedDate() {
        return getPickerWheel().getText();
    }


    private MobileElement getPickerWheel() {
        return appiumDriver.findElementByClassName("XCUIElementTypePickerWheel");
    }
}
