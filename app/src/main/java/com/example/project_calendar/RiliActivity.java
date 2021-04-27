package com.example.project_calendar;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class RiliActivity extends AppCompatActivity {


    String[][] xs = {
            {"春节", "Spring Festival"},

            {"廿一", "Twenty-one"}, {"廿二", "Twenty-two"}, {"廿三", "Twenty-three"}, {"廿四", "Twenty-four"}, {"廿五", "Twenty-five"}, {"廿六", "Twenty-six"}, {"廿七", "Twenty-seven"},
            {"廿八", "Twenty-eight"}, {"廿九", "Twenty-nine"}, {"初一", "First"}, {"初二", "Second"}, {"初三", "Third"}, {"初四", "Forth"}, {"初五", "Fifth"}, {"初六", "Sixth"}, {"初七", "Seventh"},
            {"初八", "Eighth"}, {"初九", "Ninth"}, {"初十", "Tenth"}, {"十一", "Eleventh"}, {"十二", "Twelfth"}, {"十三", "Thirteenth"}, {"十四", "Fourteen"}, {"十五", "Fifteen"},
            {"十六", "Sixteen"}, {"十七", "Seventeen"}, {"十八", "Eighteen"}, {"十九", "Nineteen"}, {"二十", "Twenty"},

            //节日
            {"地球日", "Earth Day"}, {"儿童节","Children's Day"}, {"元旦","New Year's Day"}, {"元宵节","Lantern Festival"}, {"妇女节","Working Women's Day"}, {"植树节","Arbor Day"},
            {"邮政节","Postal Day"}, {"世界气象节","World Meteorology Day"}, {"清明节","ChingMing Festival"}, {"劳动节","Labour Day"}, {"青年节","Chinese Youth Day"}, {"护士节","Nurses' Festival"},
            {"端午","Dragon Boat Festival"}, {"建党节","Party's Birthday"},{"建军节","Army's Day"},{"中秋节","Moon Festival"},{"重阳节","Double-ninth Day"},{"教师节","Teacher's Day"},
            {"国庆节","National Day"}, {"除夕","New Year's Eve"}, {"父亲节","Father's Day"}, {"母亲节","Mother's Day"}, {"愚人节","April Fool's Day"}, {"感恩节","Thanks Giving"}, {"光棍节","Singles Day"},
            {"平安夜","Christmas Eve"}, {"圣诞节","Christmas Day"}, {"情人节","Valentine's Day"}, {"消权日","Elimination Day"}, {"万圣节","Halloween"},

            //节气
            {"立春", "Beginning of Spring"}, {"雨水", "Rain Water"}, {"惊蛰", "Insects Awakening"}, {"春分", "Spring Equinox"}, {"清明", "Fresh Green"}, {"谷雨", "Grain Rain"}, {"立夏", "Beginning of Summer"},
            {"小满", "Lesser Fullness"}, {"芒种", "Grain in Ear"}, {"夏至", "Summer Solstice"}, {"小暑", "Lesser Heat"}, {"大暑", "Greater Heat"}, {"立秋", "Beginning of Autumn"},
            {"处暑", "End of Heat"}, {"白露", "White Dew"}, {"秋分", "Autumnal Equinox"}, {"寒露", "Cold Dew"}, {"霜降", "First Frost"},
            {"立冬", "Beginning of Winter"}, {"小雪", "Light Snow"}, {"大雪", "Heavy Snow"}, {"冬至", "Winter Solstice"}, {"小寒", "Lesser Cold"}, {"大寒", "Greater Cold"},

            //月份
            {"一月","January"}, {"二月","February"}, {"三月","March"}, {"四月","April"}, {"五月","May"}, {"六月","June"}, {"七月","July "}, {"八月","August"}, {"九月","September"}, {"十月","October"}, {"十一月","November"}, {"十二月","December"},

    };
    WebView mWebView;
    String url="https://www.yourchineseastrology.com/calendar/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rili);
        String va1 = getIntent().getStringExtra("va1");
        String va2 = getIntent().getStringExtra("va2");

        TextView tv1 = findViewById(R.id.va1);
        TextView tv2 = findViewById(R.id.va2);
        tv1.setText(va1);
        for (int i = 0; i < xs.length; i++) {
            if (xs[i][0].equals(va2)) {
                tv2.setText(xs[i][1]);
                break;
            }
        }

        mWebView = findViewById(R.id.webView);
        WebSettings settings = mWebView.getSettings();
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);

        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        //DOM storage API
        mWebView.getSettings().setDomStorageEnabled(true);
        //database storage API
        mWebView.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath()+"/webcache";
        //      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
//        Log.i(TAG, "cacheDirPath="+cacheDirPath);

        mWebView.getSettings().setDatabasePath(cacheDirPath);
        //Application Caches
        mWebView.getSettings().setAppCachePath(cacheDirPath);
        //Application Caches
        mWebView.getSettings().setAppCacheEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (null != mWebView) {
                    mWebView.loadUrl("javascript:isApp(2)");
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 接受所有网站的证书
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            //方法中的onProgressChanged就代表了其速度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //首先我们的进度条是隐藏的
//                pb_loading.setVisibility(View.VISIBLE);
//                //把网页加载的进度传给我们的进度条
//                pb_loading.setProgress(newProgress);
//                if (newProgress == 100) {
//                    //加载完毕让进度条消失
//                    pb_loading.setVisibility(View.GONE);
//                }
                super.onProgressChanged(view, newProgress);
            }
        });


        mWebView.loadUrl(url);

    }
}