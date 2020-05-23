package page.datepicker.android.SDK29;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.BasePage;
import page.datepicker.DatePicker;
import util.AppiumUtil;
import util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static io.appium.java_client.MobileBy.AndroidUIAutomator;

public class AndroidDatePicker extends BasePage implements DatePicker {

    private AppiumDriver<MobileElement> appiumDriver;
    private final String YEAR_ID = "android:id/text1";

    public AndroidDatePicker(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }

    @Override
    public void selectDate(String date) {
        final String yearLocatorId = "android:id/date_picker_header_year";
        appiumDriver.findElementById(yearLocatorId).click();

        Date inputDate = DateUtil.parseInputDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        MobileElement yearPickerContainer = appiumDriver
                .findElement(AndroidUIAutomator("new UiSelector().resourceId(\"android:id/date_picker_year_picker\")"));
        scrollToYear(calendar, yearPickerContainer);
        tapToMonth(calendar);
        SimpleDateFormat accessibilityIdDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        appiumDriver.findElementByAccessibilityId(accessibilityIdDateFormat.format(inputDate)).click();
    }

    @Override
    public String getSelectedDate() {
        return appiumDriver.findElement(By.id("android:id/date_picker_header_date")).getText();
    }

    private void scrollToYear(Calendar calendar, MobileElement element) {
        String yearStr = String.valueOf(calendar.get(Calendar.YEAR));
        while (true) {
            List<Integer> years = appiumDriver.findElements(By.id(YEAR_ID)).stream().map(WebElement::getText)
                    .map(Integer::valueOf).collect(Collectors.toList());

            if (years.contains(calendar.get(Calendar.YEAR))) {
                appiumDriver.findElements(By.id(YEAR_ID)).stream().filter(
                        item -> item.getText().equals(yearStr)
                ).findFirst().get().click();
                break;
            }
            if (years.get(years.size() / 2) > calendar.get(Calendar.YEAR)) {
                AppiumUtil.swipeUp(appiumDriver, element.getCenter(), 300);
            } else {
                AppiumUtil.swipeDown(appiumDriver, element.getCenter(), 300);
            }
        }
    }

    private void tapToMonth(Calendar calendar) {
        int tapCount = getNumberOfTaps(calendar.get(Calendar.MONTH));
        if (tapCount < 0) {
            // Tap Left until the expected month is reached.
            tapElementRepeatedly("android:id/next", tapCount);
        } else if (tapCount > 0) {
            // Tap Left until the expected month is reached.
            tapElementRepeatedly("android:id/prev", tapCount);
        }
    }

    private void tapElementRepeatedly(String id, int tapCount) {
        for (int i = 0; i < Math.abs(tapCount); i++) {
            appiumDriver.findElement(By.id(id)).click();
        }
    }

    private int getNumberOfTaps(int expectedMonth) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) - expectedMonth;
    }
}
