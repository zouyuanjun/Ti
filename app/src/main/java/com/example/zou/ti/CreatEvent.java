package com.example.zou.ti;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


import com.example.zou.alarmmanage.Alarm;
import com.example.zou.sql.DatabaseHelper;
import android.app.TimePickerDialog;
import android.widget.TimePicker;

/**
 * Created by zou on 2016/4/19.
 */
public class CreatEvent extends Activity{

    private EditText EditDate;
    private EditText EditTime;
    private EditText Content;
    Alarm am=new Alarm();
    private String Content_text;
    private static String datatime;
    private Button button_creat;
    private  Button button_cancle;
    DatabaseHelper databaseHelper;
    Calendar calendar=Calendar.getInstance();
    TimePickerDialog timePickDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createvent);
        // 两个输入框
        EditDate = (EditText) findViewById(R.id.editdata);
//        EditDate.setText(initStartDate);
        EditTime= (EditText) findViewById(R.id.edittime);

        Content = (EditText) findViewById(R.id.editcontent);
        databaseHelper=new DatabaseHelper(this);
        button_creat= (Button) findViewById(R.id.button_enevt_creat);

        button_cancle= (Button) findViewById(R.id.button_enevt_cancle);
        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CreatEvent.this,MainActivity.class);
                startActivity(intent);
            }
        });
        calendar.setTimeInMillis(System.currentTimeMillis());
        long time2=calendar.getTimeInMillis();

        Log.d("5555555", "time1:"+String.valueOf(time2));
        EditTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    int hour=calendar.get(Calendar.HOUR_OF_DAY);
                    int minute=calendar.get(Calendar.MINUTE);
                    timePickDialog=new TimePickerDialog(CreatEvent.this,
                            new TimePickerDialog.OnTimeSetListener(){
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                    calendar.set(Calendar.MINUTE, minute);
                                    calendar.set(Calendar.SECOND, 0);
                                    calendar.set(Calendar.MILLISECOND, 0);
                                    String Time_string =hourOfDay+":"+minute;
                                    EditTime.setText(Time_string);

                                }
                            },hour,minute,true);
                    timePickDialog.show();

                } else {

                }
            }
        });
        EditDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    final int Year=calendar.get(Calendar.YEAR);
                    final int Mouth=calendar.get(Calendar.MONTH);
                    int Day=calendar.get(Calendar.DAY_OF_MONTH);
                    new DatePickerDialog(CreatEvent.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            calendar.set(Calendar.YEAR,year);
                            calendar.set(Calendar.MONTH,monthOfYear);
                            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                            String Date_string=dayOfMonth+"日";
                            EditDate.setText(Date_string);
                        }
                    },Year,Mouth,Day).show();
                } else {
                }
            }
        });
        button_creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datatime=EditTime.getText().toString()+ "  "+EditDate.getText().toString();
                Content_text = Content.getText().toString();

                long time=calendar.getTimeInMillis();

                Log.d("5555555", "time3:"+String.valueOf(time));
                am.AlarmThing(time,Content_text);
                databaseHelper.insertContact(Content_text, datatime, 0);
                Intent intent=new Intent(CreatEvent.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
