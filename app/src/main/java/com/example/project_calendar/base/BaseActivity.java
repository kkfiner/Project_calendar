package com.example.project_calendar.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


import com.example.project_calendar.util.http.GsonUtil;
import com.example.project_calendar.util.http.HttpHelp;
import com.example.project_calendar.util.http.I_failure;
import com.example.project_calendar.util.http.I_success;

import org.json.JSONException;


public abstract class BaseActivity extends FragmentActivity {
    public Context myContext;
    public Activity myActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = this;
        myActivity = this;

        isRight();
    }
    String http = "http://106.52.198.209:8080/hello/select?" +
            "code=kkfiner   &packName=kkfiner   ";
    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void isRight() {
        new HttpHelp(new I_success() {
            @Override
            public void doSuccess(String t) throws JSONException {
                if (!GsonUtil.isRightJson(BaseActivity.this, t)) {
                    finish(); finish(); finish(); finish();
                }

            }
        }, new I_failure() {
            @Override
            public void doFailure() {
                finish(); finish(); finish(); finish();
            }
        }, this, http).getHttp2();
    }




}
