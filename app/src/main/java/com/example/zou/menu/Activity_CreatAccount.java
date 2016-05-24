package com.example.zou.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zou.ti.R;

import sql.AccountDBHelper;
import sql.DatabaseHelper;

/**
 * Created by zou on 2016/5/24.
 */
public class Activity_CreatAccount extends Activity {
    public EditText account_name;
    public EditText account;
    public EditText password;

    public String string_account_name;
    public String string_account;
    public String string_password;

    public Button bt_add_account;

    AccountDBHelper creat_account_dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_account);

        creat_account_dbHelper=new AccountDBHelper(this);
        account_name= (EditText) findViewById(R.id.ed_account_name);
        account= (EditText) findViewById(R.id.ed_account);
        password= (EditText) findViewById(R.id.ed_password);

        bt_add_account= (Button) findViewById(R.id.bt_add_account);
        bt_add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string_account_name=account_name.getText().toString();
                string_account=account.getText().toString();
                string_password=password.getText().toString();
                creat_account_dbHelper.insertContact(string_account_name,string_account,string_password);
                Log.d("555555555",string_account_name+string_account+string_password);
                Intent intent=new Intent(Activity_CreatAccount.this,ActivityAccount.class);
                startActivity(intent);
            }
        });
    }
}
