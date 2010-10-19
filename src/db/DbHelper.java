/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

//import dbtest.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author yxdeng
 */
public class DbHelper extends SQLiteOpenHelper implements DbConstants {

    private static final int DATABASE_VERSION = 1;
    private static final String DBNAME = "mfc_db.db";

    public DbHelper(Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        throw new UnsupportedOperationException("Not supported yet.");
        db.execSQL("CREATE TABLE " + TABLENAME + " ("
                + android.provider.BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BEGINTIME + " TEXT NOT NULL,"
                + ENDTIME + " TEXT NOT NULL,"
                + REMINDTIME + " TEXT  NULL,"
                + PLACE + " TEXT  NULL,"
                + SUBJECT + " TEXT  NULL,"
                + CONTENT + " TEXT  NULL,"
                + REPEATTYPE + " INTEGER NOT NULL,"
                + EXTRAINFO + " TEXT  NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
//        throw new UnsupportedOperationException("Not supported yet.");
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(db);
    }

    public void addNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(DbConstants.BEGINTIME, note.getBeginTime());
        values.put(DbConstants.CONTENT, note.getContent());
        values.put(DbConstants.ENDTIME, note.getEndTime());
        values.put(DbConstants.EXTRAINFO, note.getExtraInfo());
        values.put(DbConstants.PLACE, note.getPlace());
        values.put(DbConstants.REMINDTIME, note.getRemindTime());
        values.put(DbConstants.REPEATTYPE, note.getRepeatType());
        values.put(DbConstants.SUBJECT, note.getSubject());
        getReadableDatabase().insertOrThrow(TABLENAME, null, values);
    }
}
