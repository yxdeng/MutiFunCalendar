/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
//import android.view.View;

/**
 *
 * @author yxdeng
 */
public class SetMonthView extends Dialog{

    public SetMonthView(Context context, MonthView mainView) {
        super(context);
        this.month = mainView;

    }
//    public SetMonthView(Context context, MainView mainView) {
//        super(context);
//        this.month = mainView;
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("month setting ...");
        this.setContentView(org.me.mutifuncalendar.R.layout.monthview);
        dp = (DatePicker) this.findViewById(org.me.mutifuncalendar.R.id.dateId);
        Button button = (Button) this.findViewById(org.me.mutifuncalendar.R.id.backbutton);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
//                throw new UnsupportedOperationException("Not supported yet.");
                goBack();
            }
        });
        button = (Button) this.findViewById(org.me.mutifuncalendar.R.id.scanbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
//                throw new UnsupportedOperationException("Not supported yet.");
                scanCurrentDay();
            }
        });
//        dp.
    }
    private void goBack(){
        this.dismiss();
    }

    private void scanCurrentDay(){
//        System.out.println("dp: "+dp.getYear()+" "+dp.getMonth()+" "+dp.getDayOfMonth());
        month.getMyCalendar().setDate(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
        month.setSelectDay(dp.getDayOfMonth());
        month.invalidate();
        this.dismiss();
    }
//    @Override
//    public void setOnKeyListener(OnKeyListener onKeyListener) {
//        super.setOnKeyListener(onKeyListener);
//    }

    private DatePicker dp;
    private MonthView month;
}
