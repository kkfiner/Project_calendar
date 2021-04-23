package com.example.project_calendar.base;


import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.litepal.LitePalApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


//import cn.jpush.android.api.JPushInterface;


public class MyApplication extends LitePalApplication {

    public static final String TAG = "-----------";


    public static String curUser;


    @Override
    public void onCreate() {
        super.onCreate();


        Logger.init("Logger").methodCount(2).logLevel(LogLevel.FULL);//初始化Logger,设置开启、关闭日志输出
        initOkGo();//init okgo
    }

    //init okgo
    private void initOkGo() {
        long timeOut =  20;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(timeOut, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeOut, TimeUnit.MILLISECONDS);
        builder.connectTimeout(timeOut, TimeUnit.MILLISECONDS);
    }



}
