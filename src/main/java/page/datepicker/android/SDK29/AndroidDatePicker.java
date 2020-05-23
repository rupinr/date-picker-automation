package page.datepicker.android.SDK29;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.BasePage;
import page.datepicker.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AndroidDatePicker  extends BasePage implements DatePicker {

    private AppiumDriver<MobileElement> appiumDriver;

    public AndroidDatePicker(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }

    @Override
    public void selectDate(String date) {
        System.out.println("ANDROID DATE PICKER");
        appiumDriver.findElementById("android:id/date_picker_header_year").click();

        MobileElement element = (MobileElement) appiumDriver.findElement(MobileBy.AndroidUIAutomator(
                "new UiSelector().resourceId(\"android:id/date_picker_year_picker\")"));

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = null;
        try {
            dt = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        String yearStr = String.valueOf(calendar.get(Calendar.YEAR));

        while (true) {
            List<Integer> years = appiumDriver.findElements(By.id("android:id/text1")).stream().map(WebElement::getText)
                    .map(Integer::valueOf).collect(Collectors.toList());


            if (years.contains(calendar.get(Calendar.YEAR))) {
                appiumDriver.findElements(By.id("android:id/text1")).stream().filter(
                        item -> item.getText().equals(yearStr)
                ).findFirst().get().click();
                break;
            }

            if (years.get(years.size() / 2) > calendar.get(Calendar.YEAR)) {
                scrollUp(element);
            } else {
                scrollDown(element);
            }
        }

        int taps = getTaps(calendar.get(Calendar.MONTH));

        if(taps<0){
            //tap right
            for (int i=0; i<Math.abs(taps); i++) {
                appiumDriver.findElement(By.id("android:id/next")).click();

            }

        }
        else if (taps>0) {
            //tap left
            for (int i=0; i<Math.abs(taps); i++) {
               appiumDriver.findElement(By.id("android:id/prev")).click();
            }
        }

        SimpleDateFormat df2 = new SimpleDateFormat("dd MMMM yyyy");
        appiumDriver.findElementByAccessibilityId(   df2.format(dt)).click();


    }

    public void scrollDown(MobileElement element) {
        new TouchAction<>(appiumDriver)
                .press(PointOption.point(element.getCenter().x,element.getCenter().y+300))
                .moveTo(PointOption.point(element.getCenter().x,element.getCenter().y))
                .release()
                .perform();
    }

    public void scrollUp(MobileElement element) {
        new TouchAction<>(appiumDriver)
                .press(PointOption.point(element.getCenter().x,element.getCenter().y))
                .moveTo(PointOption.point(element.getCenter().x,element.getCenter().y+300))
                .release()
                .perform();
    }

    private int getTaps(int expectedMonth) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH)-expectedMonth;
    }
}
