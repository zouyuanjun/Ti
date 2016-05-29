package com.example.zou.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zou.ti.R;

import java.util.List;

import com.example.zou.sql.AccountDBHelper;

/**
 * Created by zou on 2016/5/21.
 */
public class Account_Adapter extends BaseAdapter{

    private static int position;
    private LayoutInflater maccountInflater;
    private List<Account_bean> maccounttList;

    public Account_Adapter(Context context) {
        maccountInflater=LayoutInflater.from(context);
        AccountDBHelper accountDBHelper=new AccountDBHelper(context);
        maccounttList=accountDBHelper.getAllContacts();
    }

    @Override
    public int getCount() {
        return maccounttList.size();
    }

    @Override
    public Object getItem(int position) {
        return maccounttList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=maccountInflater.inflate(R.layout.account_item,null);
        TextView account_name= (TextView) view.findViewById(R.id.account_name);
        TextView account= (TextView) view.findViewById(R.id.account);
        TextView password= (TextView) view.findViewById(R.id.password);
        Account_bean bean=maccounttList.get(position);
        account.setText(bean.Account);
        account_name.setText(bean.Account_name);
        password.setText(bean.Password);


        return view;
    }
}
