/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.mutifuncalendar;

import android.app.Activity;
import android.os.Bundle;
import view.MainView;

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

}
