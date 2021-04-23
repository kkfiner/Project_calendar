//package com.example.project_calendar.weather;
//
//import android.app.Service;
//import android.content.Intent;
//
//import java.util.logging.Handler;
//
//
//public class SyncService extends Service {
//
//    private Handler mHandler;
//    // default interval for syncing data
//    public static final long DEFAULT_SYNC_INTERVAL = 600 * 1000;
//
//    // task to be run here
//    private Runnable runnableService = new Runnable() {
//        @Override
//        public void run() {
//            syncData();
//            // Repeat this runnable code block again every ... min
//            mHandler.postDelayed(runnableService, Constant.DEFAULT_SYNC_INTERVAL);
//        }
//    };
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        // Create the Handler object
//        mHandler = new Handler();
//        // Execute a runnable task as soon as possible
//        mHandler.post(runnableService);
//
//        return START_STICKY;
//    }
//
//    private synchronized void syncData() {
//        // call your rest service here
//    }
//}