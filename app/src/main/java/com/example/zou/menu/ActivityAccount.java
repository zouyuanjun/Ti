package com.example.zou.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zou.ti.ActivityCollector;
import com.example.zou.ti.R;

/**
 * Created by zou on 2016/5/20.
 */
public class ActivityAccount extends Activity{

    Button mbt_creat_account;
    ListView mlistview_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ActivityCollector.addActivity(this);
        mbt_creat_account= (Button) findViewById(R.id.bt_creatAccount);
        mlistview_account= (ListView) findViewById(R.id.id_lv_account);
        mbt_creat_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityAccount.this,Activity_CreatAccount.class);
                startActivity(intent);
            }
        });
        mlistview_account.setAdapter(new Account_Adapter(this));
    }
}
