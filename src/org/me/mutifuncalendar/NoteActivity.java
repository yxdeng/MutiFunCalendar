/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.mutifuncalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 *
 * @author yxdeng
 */
public class NoteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.note_navigation);
        findViews();
        setListener();
    }

    private void findViews() {
        backButton = (Button) this.findViewById(R.id.note_back);
        notesButton = (Button) this.findViewById(R.id.note_catalog);
        addNoteButton = (Button) this.findViewById(R.id.add_note);
    }

    private void setListener() {
        backButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                 finish();
            }
        });
        
        notesButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                 showNotes();
            }
        });

        addNoteButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                 showEditNote();
            }
        });
    }

    private void showNotes(){
        this.setContentView(R.layout.note_catalog);
    }

    private void showEditNote(){
        this.setContentView(R.layout.note_edit);
    }

    private Button notesButton;
    private Button backButton;
    private Button addNoteButton;
}
