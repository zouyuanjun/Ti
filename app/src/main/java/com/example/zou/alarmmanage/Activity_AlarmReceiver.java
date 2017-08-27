package com.example.zou.alarmmanage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

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
    PowerManager pm = null;
    PowerManager.WakeLock mWakelock = null;
    MediaPlayer mMediaPlayer = null;
    Vibrator vibrator = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakelock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,
                "AlarmActivity");
        mWakelock.setReferenceCounted(false);
        mWakelock.acquire();
        playRingtone();

        setContentView(R.layout.activity_myreceiver);
        ActivityCollector.addActivity(this);
        Intent intent = getIntent();
        tga = intent.getStringExtra("tga");
        msg = intent.getStringExtra("msg");
        time = intent.getLongExtra("time", 0);
        start_time = intent.getLongExtra("start_time", 0);
        if (tga.equals("0")) {
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
        } else if (tga.equals("15 分钟")) {interval_time = 60 * 1000*15; LateThing();}
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
                Log.d("5555555555",tga+"延迟时TGA");
                Log.d("55555555","interval:"+String.valueOf(time+interval_time)+"间隔时间");
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

    public void playRingtone()
    {
        try
        {
            Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(Activity_AlarmReceiver.this, alert);
            // final AudioManager audioManager = (AudioManager)
            // mContext.getSystemService(Context.AUDIO_SERVICE);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern =
                    { 800, 150, 400, 130 }; // OFF/ON/OFF/ON...
            vibrator.vibrate(pattern, 2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();

        mWakelock.release();
        try
        {
            if (this.mMediaPlayer != null)
            {
                if (this.mMediaPlayer.isPlaying())
                {
                    this.mMediaPlayer.stop();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            if (null != vibrator)
            {
                vibrator.cancel();
                vibrator = null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
