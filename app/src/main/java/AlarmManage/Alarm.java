package AlarmManage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.zou.ti.MainActivity;
import com.example.zou.ti.TimePickDialogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zou on 2016/5/16.
 */
public class Alarm {

    Context mcontext= MainActivity.getContext();

    public Alarm(){
    }
    public void AlarmThing(){
        AlarmManager aManager = (AlarmManager) mcontext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("MYALARMRECEIVER");
        intent.putExtra("msg","提醒时间到了");
        PendingIntent pi = PendingIntent.getBroadcast(mcontext,0,intent,0);
        long time=TimePickDialogUtil.time;
        Log.d("555555555558888","time1:"+String.valueOf(time));
        aManager.setRepeating(AlarmManager.RTC_WAKEUP, time+5,6*1000,pi);

    }
}
