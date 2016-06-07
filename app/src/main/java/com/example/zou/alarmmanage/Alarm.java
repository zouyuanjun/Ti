package com.example.zou.alarmmanage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.zou.ti.MainActivity;

import java.util.Calendar;

/**
 * Created by zou on 2016/5/16.
 */
public class Alarm {

    Context mcontext= MainActivity.getContext();

    public Alarm(){
    }
    public void AlarmThing(long time,long start_time,String Content,String TGA){
        String time_diff=null;
        Calendar calendar=Calendar.getInstance();
        long time_began=calendar.getTimeInMillis();
        long diff=time-time_began;
        AlarmManager aManager = (AlarmManager) mcontext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("MYALARMRECEIVER");
        intent.putExtra("tga",TGA);
        intent.putExtra("msg","  "+Content);
        intent.putExtra("time",time);
        intent.putExtra("start_time",start_time);
        int  days = (int )(diff / (1000 * 60 * 60 * 24));
        int  hours = (int )((diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60));
        int  minutes =(int )((diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60));
        if (days!=0) time_diff=days+"天"+hours +"小时"+minutes+"分钟";
            else if (hours!=0) time_diff=hours +"小时"+minutes+"分钟";
                else time_diff=minutes+"分钟";
        PendingIntent pi = PendingIntent.getBroadcast(mcontext,0,intent,0);
        aManager.set(AlarmManager.RTC_WAKEUP, time,pi);
        Toast.makeText(mcontext,"将在"+time_diff+"后提醒",Toast.LENGTH_LONG).show();

    }

}
