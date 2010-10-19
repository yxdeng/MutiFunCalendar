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

/**
 *
 * @author yxdeng
 */
public class SetDayView extends Dialog{

//    public SetDayView(Context context, MainView main, DayView dv) {
//        super(context);
////        this.main = main;
//        this.dv = dv;
//    }

    public SetDayView(Context context, DayView dv) {
        super(context);
//        this.main = main;
        this.dv = dv;
    }
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("day setting ...");
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
        dv.getMyCalendar().setDate(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
//        dv.setSelectDay(dp.getDayOfMonth());
        dv.invalidate();
        this.dismiss();
    }

    private DatePicker dp;
//    private MainView main;
    private DayView dv;
}
