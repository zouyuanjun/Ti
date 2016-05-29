package com.example.zou.ti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import com.example.zou.sql.DatabaseHelper;

/**
 * Created by zou on 2016/4/2.
 */
public class historyAdapter extends BaseAdapter {
    Context context=MainActivity.getContext();
    DatabaseHelper databaseHelper;

    private LayoutInflater meventInflater;
    private List<eventbean> mhistoryList;

    public historyAdapter(Context context){
        meventInflater=LayoutInflater.from(context);
        refreshhistorylist();
    }
    public void refreshhistorylist(){
        databaseHelper=new DatabaseHelper(context);
        mhistoryList=databaseHelper.getHistory();

    }
    @Override
    public int getCount() {
        return mhistoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mhistoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=meventInflater.inflate(R.layout.eventitem,null);
        TextView eventtext=(TextView)view.findViewById(R.id.eventText);
        TextView timetext=(TextView)view.findViewById(R.id.timetext);
        CheckBox checkBox=(CheckBox)view.findViewById(R.id.id_checkBox);
        eventbean bean= mhistoryList.get(position);
        eventtext.setText(bean.event);
        timetext.setText(bean.time);
        checkBox.setChecked(true);
        return view;
    }
}
