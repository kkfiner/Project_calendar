package com.example.project_calendar;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_calendar.base.EventBus_Tag;
import com.example.project_calendar.base.QQBean;
import com.example.project_calendar.util.DateUtil;
import com.example.project_calendar.util.StrUtil;
import com.example.project_calendar.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

public class AddEditActivity extends AppCompatActivity {
    TextView Reminder_Finish, add_new_reminder;
    EditText ReminderTitleInput, ReminderTextInput;
    Spinner MonthSpinner, Date_spinner;
    int myTag;//tag
    String yuess = "1", riss = "1";
    int stype = 0;//0 add，1 edit
    QQBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        //init view
        ReminderTitleInput = findViewById(R.id.ReminderTitleInput);
        ReminderTextInput = findViewById(R.id.ReminderTextInput);
        MonthSpinner = findViewById(R.id.MonthSpinner);
        Date_spinner = findViewById(R.id.Date_spinner);

        Reminder_Finish = findViewById(R.id.Reminder_Finish);

        add_new_reminder = findViewById(R.id.add_new_reminder);
        //set view
        stype = getIntent().getIntExtra("stype", 0);
        bean = (QQBean) getIntent().getSerializableExtra("bean");
        if (stype == 0) {
            add_new_reminder.setText("Add Reminder");
        } else {
            add_new_reminder.setText("Modify Reminder");
            ReminderTitleInput.setText(bean.getName1());
            ReminderTextInput.setText(bean.getName2());
        }
//        //cancel
        Reminder_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        //right
        add_new_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp1 = ReminderTitleInput.getText().toString().trim();
                String temp2 = ReminderTextInput.getText().toString().trim();


                if (
                        StrUtil.isEmpty(temp1) ||
                                StrUtil.isEmpty(temp2)) {
                    ToastUtil.showToast(AddEditActivity.this, "Incomplete content");
                    return;
                }


                if (stype == 0) {//add
                    QQBean tempBean = new QQBean();
                    tempBean.setName1(temp1);
                    tempBean.setName2(temp2);
                    tempBean.setYueri(yuess + " / " + riss);

                    tempBean.setTimes(DateUtil.getTodayData_3());
                    tempBean.save();
                    if (tempBean.isSaved()) {
                        ToastUtil.showToast(AddEditActivity.this, "Successfully added");
                    }
                } else {//updata
                    ContentValues values = new ContentValues();
                    values.put("name1", temp1);
                    values.put("name2", temp2);
                    values.put("yueri", yuess + " / " + riss);

                    DataSupport.updateAll(QQBean.class, values, "times = ?", bean.getTimes());
                    ToastUtil.showToast(AddEditActivity.this, "Successfully edited");
                }

                EventBus.getDefault().post(new EventBus_Tag(1));
                finish();
            }
        });

        String[] yue = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddEditActivity.this,
                android.R.layout.simple_list_item_1, yue);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MonthSpinner.setAdapter(myAdapter);
        MonthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yuess = yue[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        String[] ri = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"


        };
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddEditActivity.this,
                android.R.layout.simple_list_item_1, ri);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Date_spinner.setAdapter(myAdapter2);
        Date_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                riss = ri[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }
}