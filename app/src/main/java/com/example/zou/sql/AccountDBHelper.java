package com.example.zou.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.zou.menu.Account_bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zou on 2016/5/24.
 */
public class AccountDBHelper extends SQLiteOpenHelper {
    List<Account_bean> account_beanList=new ArrayList<>();
    static final String KEY_ROWID = "_id";
    static final String KEY_Account_name = "account_name";
    static final String KEY_Account = "account";
    static final String KEY_Password = "password";

    static final String DATABASE_NAME = "MyAccountDB.db";
    static final String DATABASE_TABLE = "account";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE =
            "create table account( _id integer primary key autoincrement, " +
                    "account_name text not null,account text not null, password text not null);";
    Context context;

    public AccountDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.i("55555555","ONCreat");
            db.execSQL(DATABASE_CREATE);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public long insertContact(String account_name, String account, String password) {

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_Account_name, account_name);
        initialValues.put(KEY_Account, account);
        initialValues.put(KEY_Password, password);
        long rowID =db.insert(DATABASE_TABLE, null, initialValues);
        initialValues.clear();
        db.close();
        return rowID;
    }

    public void updata(int position, String account_name, String account, String passage) {

    }

    public List getAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_Account_name, KEY_Account, KEY_Password}, null, null, null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                String account_name = mCursor.getString(mCursor.getColumnIndex("account_name"));
                String account = mCursor.getString(mCursor.getColumnIndex("account"));
                String password= mCursor.getString(mCursor.getColumnIndex("password"));
                int ID=mCursor.getInt(mCursor.getColumnIndex("_id"));
                Log.d("55555555555",String.valueOf(ID)+"getallcontacts");
                account_beanList.add(new Account_bean(account_name,account, password,ID));

            }
        }
        mCursor.close();
        db.close();
       return account_beanList;
    }


    @Override
    public void close() {
        super.close();
    }


}
