package com.example.zou.ti;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import com.example.zou.sql.DatabaseHelper;

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
        ViewHolder viewHolder=null;
        if (null==convertView) {
            viewHolder = new ViewHolder();
            databaseHelper = new DatabaseHelper(context);
            eventAdapter.position = position;
            convertView = meventInflater.inflate(R.layout.eventitem, null);
            viewHolder.eventtext = (TextView) convertView.findViewById(R.id.eventText);
            viewHolder.timetext = (TextView) convertView.findViewById(R.id.timetext);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.id_checkBox);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        eventbean bean=meventList.get(position);
        final String event=bean.event;
        final String time=bean.time;
        boolean tag=bean.over;
        final int ID=bean.ID;
        viewHolder.eventtext.setText(event);
        viewHolder.timetext.setText(time);
        viewHolder.checkBox.setChecked(tag);
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("55555", "Chackbox  Click" + String.valueOf(ID));
                    databaseHelper.updata(ID, event, time, 1);
                }
            }
        });

        return convertView;
    }
    private  static class ViewHolder{
        TextView eventtext;
        TextView timetext;
        CheckBox checkBox;

    }
}
