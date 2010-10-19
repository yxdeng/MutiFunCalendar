/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.mutifuncalendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.Observable;
import java.util.Observer;
import view.DayObservable;
import view.DayView;
//import view.MainView;
import view.MonthObservable;
import view.MonthView;
import view.SetDayView;
import view.SetMonthView;

/**
 *
 * @author yxdeng
 */
public class CalendarActivity  extends Activity implements Observer{

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        System.out.println("i am create monthview!!");
        this.setContentView(new MonthView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.monthmenu, menu);
        return true;
    }

     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_back_now_id:
                setContentView(new MonthView(this));
                break;
            case R.id.menu_note_id:
                Intent intent = new Intent(this, NoteActivity.class);
                this.startActivity(intent);
                break;
        }
        return true;
    }

    public void update(Observable arg0, Object arg1) {
//        throw new UnsupportedOperationException("Not supported yet.");
        if(arg0.getClass() == MonthObservable.class){
            setContentView(new DayView(this,((MonthObservable)arg0).getCalendar()));
        }
        if(arg0.getClass() == DayObservable.class){
            setContentView(new MonthView(this,((DayObservable)arg0).getCalendar()));
        }
    }

    public void showMonthView(MonthView month){
        Dialog monthview = new SetMonthView(this,month);
        monthview.show();
    }

    public void showDayView(DayView day){
        Dialog dayview = new SetDayView(this,day);
        dayview.show();
    }
}
