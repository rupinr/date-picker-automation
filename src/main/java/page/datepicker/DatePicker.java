package page.datepicker;

import page.BasePageInterface;

public interface DatePicker extends BasePageInterface {

    /**
     * Select the given day in the Calendar
     * @param date
     */
    void selectDate(String date);

    /**
     * Get the currently selected date.
     * @return Current Selected Date
     */
    String getSelectedDate() ;
}
