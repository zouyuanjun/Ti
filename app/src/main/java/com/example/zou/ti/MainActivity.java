package com.example.zou.ti;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sql.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager mViewpage;
    private PagerAdapter mAdapter;
    private List<View> mView=new ArrayList<View>();
    private LinearLayout mTabevent;
    private LinearLayout mTabhistory;
    private Button mevent;
    private Button mhistory;
    private Button mcreat;
    private static Context context;
    private static eventAdapter eventAdapter;
    private static historyAdapter historyAdapter;

    public static com.example.zou.ti.eventAdapter getEventAdapter() {
        return eventAdapter;
    }

    public static com.example.zou.ti.historyAdapter getHistoryAdapter() {
        return historyAdapter;
    }

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("55555555", "MainActivityOnCreat: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCollector.addActivity(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mcreat=(Button)findViewById(R.id.id_button_creat);
        mcreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreatEvent.class);
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);

        context=this;
        databaseHelper=new DatabaseHelper(context);
        db=databaseHelper.getWritableDatabase();
        db.close();

        initView();
        initEvents();

    }
    public static Context getContext(){
        return context;
    }

    //初始化点击事件
    private void initEvents() {

        mevent.setOnClickListener(this);
        mhistory.setOnClickListener(this);
        mViewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewpage.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        break;
                    case 1:
                        break;
                    default:
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
//初始化ViewPage
    private void initView() {

        mViewpage=(ViewPager)findViewById(R.id.id_viewpager);
        mTabevent=(LinearLayout)findViewById(R.id.id_tab_enent);
        mTabhistory=(LinearLayout)findViewById(R.id.id_tab_history);

        mevent=(Button)findViewById(R.id.id_button_event);
        mhistory=(Button)findViewById(R.id.id_button_history);
        //实例化一个LayoutInflater对象
        LayoutInflater mInflate=LayoutInflater.from(this);
        View event=mInflate.inflate(R.layout.event, null);
        View eventitem=mInflate.inflate(R.layout.eventitem, null);
        View history=mInflate.inflate(R.layout.history,null);
        //取得event这个ViewPage中的listview
        ListView eventListView=(ListView)event.findViewById(R.id.eventListView);

        eventAdapter=new eventAdapter(this);
        eventListView.setAdapter(eventAdapter);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CreatEvent.class);
                startActivity(intent);
            }
        });

        ListView historytListView=(ListView)history.findViewById(R.id.historytListView);
        historyAdapter=new historyAdapter(this);
        historytListView.setAdapter(historyAdapter);




        mView.add(event);
        mView.add(history);

        mAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return mView.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view=mView.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mView.get(position));

                super.destroyItem(container, position, object);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0==arg1;
            }
        };


        mViewpage.setAdapter(mAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_button_event:
                mViewpage.setCurrentItem(0);
                break;
            case R.id.id_button_history:
                mViewpage.setCurrentItem(1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();

        }

        return false;

    }
    /**监听对话框里面的button点击事件*/
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
    {
        public void onClick(DialogInterface dialog, int which)
        {
            switch (which)
            {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    ActivityCollector.finishAll();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };
    private  void refresh(){
        finish();
        Intent intent=new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
