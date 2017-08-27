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
        ViewHolder viewHolder=null;
        if (null==viewHolder) {
            viewHolder = new ViewHolder();

            convertView = meventInflater.inflate(R.layout.eventitem, null);
            viewHolder.eventtext = (TextView) convertView.findViewById(R.id.eventText);
            viewHolder.timetext = (TextView) convertView.findViewById(R.id.timetext);
            viewHolder. checkBox = (CheckBox) convertView.findViewById(R.id.id_checkBox);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        eventbean bean= mhistoryList.get(position);
        viewHolder.eventtext.setText(bean.event);
        viewHolder.timetext.setText(bean.time);
        viewHolder.checkBox.setChecked(true);
        return convertView;
    }
    private  static class ViewHolder{
        TextView eventtext;
        TextView timetext;
        CheckBox checkBox;

    }
}
