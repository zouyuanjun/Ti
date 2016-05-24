package sql;

/**
 * Created by zou on 2016/4/4.
 */
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.zou.ti.MainActivity;
import com.example.zou.ti.eventAdapter;
import com.example.zou.ti.eventbean;
import com.example.zou.ti.historyAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;

public  class DatabaseHelper extends SQLiteOpenHelper
    {
        List<eventbean> eventbeanList=new ArrayList<>();
        List<eventbean> historybeanList=new ArrayList<>();

        eventAdapter eventAdapter= MainActivity.getEventAdapter();
        historyAdapter historyAdapter=MainActivity.getHistoryAdapter();

        static final String KEY_ROWID = "_id";
        static final String KEY_EVENT = "event";
        static final String KEY_TIME = "time";
        static final String KEY_TAG = "tag";

        static final String DATABASE_NAME = "MyDB.db";
        static final String DATABASE_TABLE = "contacts";
        static final int DATABASE_VERSION = 1;

        static final String DATABASE_CREATE =
                "create table contacts( _id integer primary key autoincrement, " +
                        "event text not null,time text not null, tag interger not null);";
        static SQLiteDatabase db;
        Context context;
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
        }
        @Override
        public void onCreate( SQLiteDatabase db) {
            // TODO Auto-generated method stub
            try {Log.i("55555555","ONCreat");
                db.execSQL(DATABASE_CREATE);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }

        public void close() {
            db.close();
        }

        public long insertContact(String event, String time,int tag)
        {
            Log.i("555555555", "insertContact");

            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_EVENT, event);
            initialValues.put(KEY_TIME, time);
            initialValues.put(KEY_TAG, tag);
            long rowID =db.insert(DATABASE_TABLE, null, initialValues);
            initialValues.clear();
            db.close();
            return rowID;
        }
        public void updata(int position,String event,String time,int tag) {
            SQLiteDatabase db=this.getWritableDatabase();
            String[] whereArgs={String.valueOf(position)};
            ContentValues updataValues = new ContentValues();
            updataValues.put(KEY_EVENT, event);
            updataValues.put(KEY_TIME, time);
            updataValues.put(KEY_TAG, tag);
            db.update(DATABASE_TABLE, updataValues, "_id=?", whereArgs);
            updataValues.clear();
            Log.d("55555555555", "updata");
            eventAdapter.refresheventList();
            eventAdapter.notifyDataSetChanged();
            historyAdapter.refreshhistorylist();
            historyAdapter.notifyDataSetChanged();
            db.close();

        }
        //delete a particular contact
        public boolean deleteContact(long rowId)
        {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" +rowId, null) > 0;
         }
         //retreves all the contacts
        public List getAllContacts() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor mCursor = db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_EVENT, KEY_TIME, KEY_TAG}, null, null, null, null, null, null);
            if (mCursor != null) {
                while (mCursor.moveToNext()) {
                String event = mCursor.getString(mCursor.getColumnIndex("event"));
                String time = mCursor.getString(mCursor.getColumnIndex("time"));
                int tag = mCursor.getInt(mCursor.getColumnIndex("tag"));
                int ID=mCursor.getInt(mCursor.getColumnIndex("_id"));
                    Log.d("55555555555",String.valueOf(ID)+"getallcontacts");
                if (tag==0) {
                    eventbeanList.add(new eventbean(false, event, time, ID));
                    }
                }
            }
            mCursor.close();
            db.close();
                return eventbeanList;
            }

        public List getHistory() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor mCursor = db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_EVENT, KEY_TIME, KEY_TAG}, null, null, null, null, null, null);
            if (mCursor != null) {
                while (mCursor.moveToNext()) {
                    String event = mCursor.getString(mCursor.getColumnIndex("event"));
                    String time = mCursor.getString(mCursor.getColumnIndex("time"));
                    int tag = mCursor.getInt(mCursor.getColumnIndex("tag"));
                    int ID=mCursor.getInt(mCursor.getColumnIndex("_id"));
                    if (tag==1) {
                        historybeanList.add(new eventbean(true, event, time,ID));
                        Log.i("55555555", "getHistory");}
                }
            }
            mCursor.close();
            db.close();
            return historybeanList;
        }


        //retreves a particular contact
//    public Cursor getEvent(long rowId) throws SQLException
//    {
//        Cursor mCursor =
//                db.query(true, DATABASE_TABLE, new String[]{ KEY_ROWID,
//                        KEY_NAME, KEY_EMAIL}, KEY_ROWID + "=" + rowId, null, null, null, null, null);
//        if (mCursor != null)
//            mCursor.moveToFirst();
//        return mCursor;
//    }
    //updates a contact
//    public boolean updateContact(long rowId, String name, String email)
//    {
//        ContentValues args = new ContentValues();
//        args.put(KEY_NAME, name);
//        args.put(KEY_EMAIL, email);
//        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" +rowId, null) > 0;
//    }
    }
