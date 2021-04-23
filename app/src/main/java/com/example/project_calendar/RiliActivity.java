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

import com.example.project_calendar.base.BaseActivity;

public class RiliActivity extends BaseActivity {


    String[][] xs = {
            {"初十", "ten"},
            {"地球日", "earth day"},
            {"十二", "twelve"},
            {"十三", "thirteen"},


    };
    WebView mWebView;
    String url="https://wannianrili.bmcx.com/";

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
        // turn DOM storage API
        mWebView.getSettings().setDomStorageEnabled(true);
        //turn database storage API
        mWebView.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath()+"/webcache";
        //      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
//        Log.i(TAG, "cacheDirPath="+cacheDirPath);
        //设置数据库缓存路径
        mWebView.getSettings().setDatabasePath(cacheDirPath);
        //Application Caches
        mWebView.getSettings().setAppCachePath(cacheDirPath);
        //turn Application Caches
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
                handler.proceed();
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            //方法中的onProgressChanged就代表了其速度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                super.onProgressChanged(view, newProgress);
            }
        });


        mWebView.loadUrl(url);

    }
}