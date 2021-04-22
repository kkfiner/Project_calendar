package com.example.project_calendar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.project_calendar.weather.CityPreference;
import com.example.project_calendar.weather.WeatherFragment;

public class WeatherActivity extends AppCompatActivity {
    ProgressDialog pd;
    String txtJson;
    private TextView mTextView;

    //private static final int CONTENT_VIEW_ID = 10101010;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


//        FrameLayout frame = new FrameLayout(this);
//        frame.setId(CONTENT_VIEW_ID);
//        setContentView(frame, new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
//
//        if (savedInstanceState == null) {
//            Fragment newFragment = new WeatherFragment();
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.add(CONTENT_VIEW_ID, newFragment).commit();
//        }
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new WeatherFragment())
//                    .commit();
//        }

        FragmentManager fm = getSupportFragmentManager();
        fragment = fm.findFragmentByTag("WeatherFragment");
        if (fragment == null) {
            androidx.fragment.app.FragmentTransaction ft = fm.beginTransaction();
            fragment =new WeatherFragment();
            ft.add(android.R.id.content,fragment,"WeatherFragment");
            ft.commit();

        }
    }

  //  @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == R.id.change_city){
//            showInputDialog();
//        }
//        return false;
//    }



}