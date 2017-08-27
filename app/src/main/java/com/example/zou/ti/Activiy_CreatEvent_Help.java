package com.example.zou.ti;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by zou on 2016/7/13.
 */
public class Activiy_CreatEvent_Help extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_event_help);
        ActivityCollector.addActivity(this);
    }
}
