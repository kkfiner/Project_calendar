package com.example.project_calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.project_calendar.base.BaseRecyclerAdapter;
import com.example.project_calendar.base.EventBus_Tag;
import com.example.project_calendar.base.MyRVViewHolder;
import com.example.project_calendar.base.QQBean;
import com.example.project_calendar.util.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CalendarView.OnCalendarSelectListener{
    @BindView(R.id.lv)
    RecyclerView lv;
    @BindView(R.id.tv_add)
    TextView tv_add;
    @BindView(R.id.todayText)
    TextView todayText;
    @BindView(R.id.calendarView)
    CalendarView calendarView;


    private String selectTime = "";
    private String todayTime = "";
    private List<QQBean> itemBeanList = new ArrayList();
    private MyAdapter myAdapter;
    private boolean firstTime = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().register(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Date c = java.util.Calendar.getInstance().getTime();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });


        todayTime = calendarView.getCurYear() + "-" + append0(calendarView.getCurMonth()) + "-" + append0(calendarView.getCurDay());

        calendarView.setOnCalendarSelectListener(this);
        calendarView.setRange(2020, 1, 1, calendarView.getCurYear(), 12, 31);
        calendarView.scrollToCalendar(calendarView.getCurYear(), calendarView.getCurMonth(), calendarView.getCurDay());

        findViewById(R.id.toToday).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.scrollToCalendar(calendarView.getCurYear(), calendarView.getCurMonth(), calendarView.getCurDay());

            }
        });

        initData();
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
        if(id==R.id.Reminder){
           gotoReminder();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"setup click recieved in MainActivity",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void gotoTIH(View view){
       Intent intent=new Intent(this,TIHActivity.class);
       startActivity(intent);
    }

    public void gotoReminder(){
        Intent intent=new Intent(this,ReminderActivity.class);
        startActivity(intent);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        initAdapter();
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new QQDialog(MainActivity.this, 0).showDialog();
                Intent intent = new Intent(MainActivity.this,AddEditActivity.class);
                intent.putExtra("stype",0);
                startActivity(intent);
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initAdapter() {
        //init listview
        itemBeanList.clear();

        @SuppressLint("WrongConstant")
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        if (null == manager)
            return;
        lv.setLayoutManager(manager);
        myAdapter = new MyAdapter(MainActivity.this, itemBeanList, R.layout.item_meal);
        lv.setAdapter(myAdapter);
        EventBus.getDefault().post(new EventBus_Tag(1));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    class MyAdapter extends BaseRecyclerAdapter<QQBean> {

        private TextView tv_name, tv_up, tv_del, tv_time;
        private int selPosi;

        public void setSelPosi(int selPosi) {
            this.selPosi = selPosi;
        }

        public MyAdapter(Context context, List<QQBean> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void setView(MyRVViewHolder holder, final QQBean bean, int position) {
            if (null == holder || null == bean)
                return;
            //init view
            tv_del = holder.getView(R.id.tv_del);
            tv_up = holder.getView(R.id.tv_up);
            tv_name = holder.getView(R.id.tv_name);
            tv_time = holder.getView(R.id.tv_time);

            TextView tv_name1 = holder.getView(R.id.tv_name1);
            TextView tv_name2 = holder.getView(R.id.tv_name2);



            //set view
            tv_name.setText("" + bean.getName1());
            tv_name1.setText("" + bean.getName2());



            tv_time.setText(bean.getYueri());
            tv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataSupport.deleteAll(QQBean.class, "times = ? ", bean.getTimes());
                    ToastUtil.showToast(MainActivity.this, "Successfully deleted");
                    EventBus.getDefault().post(new EventBus_Tag(1));
                }
            });
            tv_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    new QQDialog(MainActivity.this, 1, bean).showDialog();
                    Intent intent = new Intent(MainActivity.this,AddEditActivity.class);
                    intent.putExtra("stype",1);
                    intent.putExtra("bean",bean);
                    startActivity(intent);

                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBus_Tag event) {
        switch (event.getTag()) {
            case 1:
                itemBeanList.clear();
                List<QQBean> temp = DataSupport.findAll(QQBean.class);//check list Comment
                itemBeanList.addAll(temp);
                myAdapter.notifyDataSetChanged();
                break;
        }
    }

    private String append0(int value) {
        if (value < 10) {
            return "0" + value;
        }
        return String.valueOf(value);
    }


    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        //todayText.setText(calendar.getYear() + "Year" + calendar.getMonth() + "Month");
        todayText.setText(calendar.getMonth()+ " , "+ calendar.getYear());
        String time = calendar.getYear() + "-" + append0(calendar.getMonth()) + "-" + append0(calendar.getDay());

        if (!selectTime.equals(time)) {
            if (firstTime) {
                if (todayTime.equals(time)) {
                    firstTime = false;
                    selectTime = time;
                }
            } else {
                selectTime = time;
            }
            if (!"".equals(selectTime)) {

//                initData(selectTime);
            }
        }
    }


}