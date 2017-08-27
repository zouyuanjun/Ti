package com.example.zou.ti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.zou.alarmmanage.Activity_AlarmReceiver;

/**
 * Created by zou on 2016/5/19.
 */
public class MyReceiver extends BroadcastReceiver{

    public void onReceive(Context context, Intent intent){

        String msg = intent.getStringExtra("msg");
        String tga=intent.getStringExtra("tga");
        long time=intent.getLongExtra("time",0);
        long  start_time=intent.getLongExtra("start_time",0);
        Intent intent1=new Intent(context, Activity_AlarmReceiver.class);
        intent1.putExtra("msg",msg);
        intent1.putExtra("time",time);
        intent1.putExtra("tga",tga);
        intent1.putExtra("start_time",start_time);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);

    }

}
