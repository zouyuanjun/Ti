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

import com.example.zou.ti.R;

/**
 * Created by zou on 2016/5/29.
 */
public class Activity_AlarmReceiver extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreceiver);
        Intent intent=getIntent();
        String msg=intent.getStringExtra("msg");
        long  time=intent.getLongExtra("time",0);
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("提示:");
        alert.setMessage(msg);
        alert.setNegativeButton("延迟10分钟", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=alert.create();
//        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
        Log.d("5555555",msg);
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
