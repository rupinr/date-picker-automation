package page.datepicker;

import page.BasePageInterface;

public interface DatePicker extends BasePageInterface {

    public void selectDate(String date);

    public String getSelectedDate() ;
}
