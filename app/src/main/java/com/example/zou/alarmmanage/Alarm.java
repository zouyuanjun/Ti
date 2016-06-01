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
    public void AlarmThing(long time,String Content){
        String time_diff=null;
        Calendar calendar=Calendar.getInstance();
        long time_began=calendar.getTimeInMillis();
        long diff=time-time_began;
        AlarmManager aManager = (AlarmManager) mcontext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("MYALARMRECEIVER");
        intent.putExtra("msg","  "+Content);
        intent.putExtra("time",time);
        int  days = (int )(diff / (1000 * 60 * 60 * 24));
        int  hours = (int )((diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60));
        int  minutes =(int )((diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60));
        if (days!=0) time_diff=days+"天"+hours +"小时"+minutes+"分钟";
            else if (hours!=0) time_diff=hours +"小时"+minutes+"分钟";
                else time_diff=minutes+"分钟";
        PendingIntent pi = PendingIntent.getBroadcast(mcontext,0,intent,0);
        Log.d("555555555558888","time1:"+String.valueOf(time));
        aManager.set(AlarmManager.RTC_WAKEUP, time,pi);
        Toast.makeText(mcontext,"将在"+time_diff+"后提醒",Toast.LENGTH_LONG).show();

    }
}
