package com.example.project_calendar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.haibin.calendarview.CalendarLayout;

public class SettingsActivity extends AppCompatActivity {
    TextView changeCalendarView;
    TextView about;
    CalendarLayout mcalendarlayout;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");
        about=findViewById(R.id.setting_about);
        about.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("About this app");
                builder.setMessage("This app is developed for CMPSC475");
                builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
//        calendarView=findViewById(R.id.calendarView);
//        changeCalendarView=findViewById(R.id.changecalendarview);
//        changeCalendarView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SettingsActivity.this,MainActivity.class);
//                changeView();
//                startActivity(intent);
//
//            }
//        });
    }
//    public void changeView(){
//        mcalendarlayout.shrink();
//        System.out.println("shrink");
//    }
}