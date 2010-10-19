/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dbtest;

import android.app.Activity;
//import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;

/**
 *
 * @author yxdeng
 */
public class DBTestActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(org.me.mutifuncalendar.R.layout.note_edit);
        dh = new DbHelper(this);
//        dh.getWritableDatabase().i
       findViews();
        savebutton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
//                throw new UnsupportedOperationException("Not supported yet.");
                dh.getReadableDatabase();
            }
        });
    }

    private void findViews(){
        savebutton = (Button) this.findViewById(org.me.mutifuncalendar.R.id.noteedit_save);
        dp = (DatePicker) this.findViewById(org.me.mutifuncalendar.R.id.noteedit_date);
        tp = (TimePicker) this.findViewById(org.me.mutifuncalendar.R.id.noteedit_time);
        place = (EditText) this.findViewById(org.me.mutifuncalendar.R.id.noteedit_place);
        endtimegroup = (RadioGroup) this.findViewById(org.me.mutifuncalendar.R.id.noteedit_endtime);
        remindtimegroup = (RadioGroup) this.findViewById(org.me.mutifuncalendar.R.id.noteedit_remindtime);
        repeattypegroup = (RadioGroup) this.findViewById(org.me.mutifuncalendar.R.id.noteedit_repeattype);
        content = (EditText) this.findViewById(org.me.mutifuncalendar.R.id.noteedit_content);
    }


    private DbHelper dh;
    private Button savebutton ;
    private DatePicker dp ;
    private TimePicker tp;
    private EditText place;
    private RadioGroup endtimegroup;
    private RadioGroup remindtimegroup;
    private RadioGroup repeattypegroup;
    private EditText content;
}
