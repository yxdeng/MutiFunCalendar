/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.mutifuncalendar;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import view.MainView;
import view.SetMonthView;

/**
 *
 * @author yxdeng
 */
public class MainActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here
//        this.setContentView(R.layout.main);
//        this.findViewById()
        this.setContentView(new MainView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
//        menu.add(0, Menu.FIRST, 0, "setting");
//        menu.add(0,Menu.FIRST+1,0,"quit");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.monthmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_back_now_id:
                setContentView(new MainView(this));
                break;
        }
        return true;
    }

    public void showMonthView(MainView main){
        Dialog monthview = new SetMonthView(this,main);
        monthview.show();
    }
}
