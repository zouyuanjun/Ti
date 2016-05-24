package AlarmManage;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.zou.ti.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zou on 2016/5/16.
 */
public class Alarm {

    Context mcontext= MainActivity.getContext();
    Date date;
    public Alarm(){
    }
    public void AlarmThing(String time){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("hh:mm dd日");
        try
        {
            date = simpleFormatter.parse(time);
            String timemill=simpleFormatter.format(date);
            Log.d("55555555555555",timemill);
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        calendar.setTime(date);
        AlarmManager aManager = (AlarmManager) mcontext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("MYALARMRECEIVER");
        intent.putExtra("msg","提醒时间到了");
        PendingIntent pi = PendingIntent.getBroadcast(mcontext,0,intent,0);
        Date date=new Date(System.currentTimeMillis());
        long time1=date.getTime();
        Log.d("555555555558888",String.valueOf(time1));
        aManager.setRepeating(AlarmManager.RTC_WAKEUP, time1+5,600*1000,pi);

    }
}
