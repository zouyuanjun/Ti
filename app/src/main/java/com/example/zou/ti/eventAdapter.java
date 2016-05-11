package com.example.zou.ti;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import sql.DatabaseHelper;

/**
 * Created by zou on 2016/4/2.
 */
public class eventAdapter extends BaseAdapter {

    Context context=MainActivity.getContext();
    DatabaseHelper databaseHelper;
    private static int position;
    private LayoutInflater meventInflater;
    private List<eventbean> meventList;
    public eventAdapter(Context context){
        meventInflater=LayoutInflater.from(context);
        refresheventList();
    }
    public void refresheventList() {
        databaseHelper=new DatabaseHelper(context);
        meventList=databaseHelper.getAllContacts();
    }
    @Override
    public int getCount() {
        return meventList.size();
    }

    @Override
    public Object getItem(int position) {
        return meventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView( final int position, View convertView, ViewGroup parent) {
        databaseHelper=new DatabaseHelper(context);
        this.position=position;
        View view=meventInflater.inflate(R.layout.eventitem,null);
        TextView eventtext=(TextView)view.findViewById(R.id.eventText);
        TextView timetext=(TextView)view.findViewById(R.id.timetext);
        CheckBox checkBox=(CheckBox)view.findViewById(R.id.id_checkBox);
        eventbean bean=meventList.get(position);
        final String event=bean.event;
        final String time=bean.time;
        boolean tag=bean.over;
        final int ID=bean.ID;
        eventtext.setText(event);
        timetext.setText(time);
        checkBox.setChecked(tag);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("55555", "Chackbox  Click" + String.valueOf(ID));
                    databaseHelper.updata(ID, event, time, 1);
                }
            }
        });

        return view;
    }
}
