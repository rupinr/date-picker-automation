package page.datepicker;

import page.BasePageInterface;

public interface DatePicker extends BasePageInterface {

    void selectDate(String date);

    String getSelectedDate() ;
}
