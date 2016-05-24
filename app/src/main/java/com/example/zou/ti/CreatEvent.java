package com.example.zou.ti;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import AlarmManage.Alarm;
import sql.DatabaseHelper;

/**
 * Created by zou on 2016/4/19.
 */
public class CreatEvent extends Activity{

    /** Called when the activity is first created. */
    private EditText EditDate;
    private EditText EditTime;
    private EditText Content;
    Alarm am=new Alarm();
    private String Content_text;
    private static String datatime;
    private Button button_creat;
    private  Button button_cancle;
    DatabaseHelper databaseHelper;
    SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日    HH:mm    ");
    Date curDate    =   new  Date(System.currentTimeMillis());//获取当前时间
    private String initStartDateTime = formatter.format(curDate); // 初始化开始时间

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createvent);
        // 两个输入框
        EditDate = (EditText) findViewById(R.id.editdata);
        EditDate.setText(initStartDateTime);
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
        EditTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    TimePickDialogUtil TimePicKDialog = new TimePickDialogUtil(
                            CreatEvent.this, initStartDateTime);
                    TimePicKDialog.dateTimePicKDialog(EditTime);
                } else {

                }
            }
        });
        EditDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    DatePickDialogUtil dateTimePicKDialog = new DatePickDialogUtil(
                            CreatEvent.this, initStartDateTime);
                    dateTimePicKDialog.dateTimePicKDialog(EditDate);
                } else {
                }
            }
        });
        button_creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datatime=EditTime.getText().toString()+ "  "+EditDate.getText().toString();
                Content_text = Content.getText().toString();
                Log.d("55555", Content_text);
                Log.d("55555",datatime);
                am.AlarmThing(datatime);
                databaseHelper.insertContact(Content_text, datatime, 0);
                Intent intent=new Intent(CreatEvent.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

}
