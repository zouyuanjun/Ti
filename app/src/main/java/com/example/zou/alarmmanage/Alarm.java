package com.example.zou.alarmmanage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.zou.ti.MainActivity;

/**
 * Created by zou on 2016/5/16.
 */
public class Alarm {

    Context mcontext= MainActivity.getContext();

    public Alarm(){
    }
    public void AlarmThing(long time,String Content){
        AlarmManager aManager = (AlarmManager) mcontext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("MYALARMRECEIVER");
        intent.putExtra("msg","  "+Content);
        intent.putExtra("time",time);
        PendingIntent pi = PendingIntent.getBroadcast(mcontext,0,intent,0);
        Log.d("555555555558888","time1:"+String.valueOf(time));
        aManager.set(AlarmManager.RTC_WAKEUP, time,pi);

    }
}
