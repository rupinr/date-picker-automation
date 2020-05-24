package page.datepicker.ios.ios_12_1;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import page.BasePage;
import page.datepicker.DatePicker;
import util.AppiumUtil;
import util.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class IOSDatePicker extends BasePage implements DatePicker {

    private AppiumDriver<MobileElement> appiumDriver;

    private Subject<MobileElement> pickerWheel = PublishSubject.create();

    private boolean dateFound = false;

    public IOSDatePicker(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }

    @Override
    public void selectDate(String date) {
        Calendar requiredDate = Calendar.getInstance();
        requiredDate.setTime(DateUtil.parseInputDate(date));
        Calendar today = Calendar.getInstance();
        final int swipeDistance = 40;
        subscribeOnPicker();
        //Scrolls as many as the number of days.
        long daysInBetween = DateUtil.daysBetween(today, requiredDate);
        if( isSameDay(requiredDate,DateUtil.parseDisplayDate(getPickerWheel().getText())) ) {
            dateFound = true;
        }
        while (!dateFound) {
            if (daysInBetween > 0) {
                AppiumUtil.swipeDown(appiumDriver, getPickerWheel().getCenter(), swipeDistance);
            } else if (daysInBetween < 0) {
                AppiumUtil.swipeUp(appiumDriver, getPickerWheel().getCenter(), swipeDistance);
            }
            if(isSameDay(requiredDate, DateUtil.parseDisplayDate(getPickerWheel().getText()))) {
                pickerWheel.onNext(getPickerWheel());
            }
        }
    }
    private void subscribeOnPicker() {
        pickerWheel.subscribe(item ->  dateFound = true);
    }

    private boolean isSameDay(Calendar requiredDate, Date parsedDate) {
        Calendar parsedDateCalendar= Calendar.getInstance();
        parsedDateCalendar.setTime(parsedDate);
        return requiredDate.get(Calendar.MONTH)==parsedDateCalendar.get(Calendar.MONTH)
                && requiredDate.get(Calendar.DATE)== parsedDateCalendar.get(Calendar.DATE);
    }

    @Override
    public String getSelectedDate() {
        return getPickerWheel().getText();
    }


    private MobileElement getPickerWheel() {
        return appiumDriver.findElementByClassName("XCUIElementTypePickerWheel");
    }
}
