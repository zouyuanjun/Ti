package com.example.zou.alarmmanage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.zou.ti.ActivityCollector;
import com.example.zou.ti.R;

/**
 * Created by zou on 2016/5/29.
 */
public class Activity_AlarmReceiver extends Activity{

    int interval_time=0;
    Alarm am = new Alarm();
    private String tga=null;
    private String msg;
    private long  time;
    private long  start_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreceiver);
        ActivityCollector.addActivity(this);
        Intent intent = getIntent();
        tga = intent.getStringExtra("tga");
        msg = intent.getStringExtra("msg");
        Log.d("554444", tga + "888888" + msg);
        time = intent.getLongExtra("time", 0);
        start_time = intent.getLongExtra("start_time", 0);


        if (tga == "0") {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("提示:");
            alert.setMessage(msg);
            alert.setNegativeButton("延迟10分钟", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Alarm am = new Alarm();
                    am.AlarmThing(time + 60 * 1000 * 10, start_time, msg, "0");
                    Activity_AlarmReceiver.this.finish();
                }
            });
            alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Activity_AlarmReceiver.this.finish();
                }
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        } else if (tga.equals("15 分钟")) {interval_time = 60 * 1000 * 15; LateThing();}
          else if (tga.equals("30 分钟")) {interval_time = 60 * 1000 * 30;LateThing();}
          else if (tga.equals("45 分钟")) {interval_time = 60 * 1000 * 45;LateThing();}
          else if (tga.equals("1个小时")) {interval_time = 60 * 1000 * 60;LateThing();}
    }
    public void LateThing(){
        final AlertDialog.Builder interval_alert = new AlertDialog.Builder(this);
        interval_alert.setTitle("提示:");
        interval_alert.setMessage(msg);
        interval_alert.setNegativeButton("延迟"+tga, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                am.AlarmThing(time + interval_time,start_time, msg, tga);
                Log.d("555777","interval:"+String.valueOf(interval_time)+"间隔时间");
                Activity_AlarmReceiver.this.finish();
            }
        });
        interval_alert.setPositiveButton("今日任务完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                am.AlarmThing(start_time+60*1000*60*24,start_time+60*1000*60*24, msg, tga);
                Activity_AlarmReceiver.this.finish();
            }
        });
        interval_alert.setNeutralButton("取消提醒", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity_AlarmReceiver.this.finish();
            }
        });
        AlertDialog alertDialog = interval_alert.create();
        alertDialog.show();
    }
    private void setAlarmParams(Notification notification) {
        //AudioManager provides access to volume and ringer mode control.
        AudioManager volMgr = (AudioManager) Activity_AlarmReceiver.this.getSystemService(Context.AUDIO_SERVICE);
        switch (volMgr.getRingerMode()) {//获取系统设置的铃声模式
            case AudioManager.RINGER_MODE_SILENT://静音模式，值为0，这时候不震动，不响铃
                notification.sound = null;
                notification.vibrate = null;
                break;
            case AudioManager.RINGER_MODE_VIBRATE://震动模式，值为1，这时候震动，不响铃
                notification.sound = null;
                notification.defaults |= Notification.DEFAULT_VIBRATE;
                break;
            case AudioManager.RINGER_MODE_NORMAL://常规模式，值为2，分两种情况：1_响铃但不震动，2_响铃+震动
                notification.defaults |= Notification.DEFAULT_SOUND;
                notification.defaults |= Notification.DEFAULT_VIBRATE;
                break;
            default:
                break;
        }
    }
}
