package com.example.project_calendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TIHActivity extends AppCompatActivity {
//today in history
    TextView tih_title;
    Button returntomain;
    Button sharelink;
    Date dateWithoutTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tih);
        Calendar cal = Calendar.getInstance();
        System.out.println("month"+ new SimpleDateFormat("MMMM/dd").format(cal.getTime()));
        String formattedMD= new SimpleDateFormat("MMMM/dd").format(cal.getTime());//3 M for shorted name of month, 4 M for full name of the month
        tih_title=findViewById(R.id.HIT_Title);
        tih_title.setText("Today in History");
        returntomain=findViewById(R.id.button_return);
        returntomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sharelink=findViewById(R.id.ShareToSocial);
        sharelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Today in History");
                    String shareMessage= "\nCheck out what happened today in history\n\n";
                    shareMessage = shareMessage + "https://www.onthisday.com/events/" + formattedMD +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl("https://www.onthisday.com/events/" + formattedMD);

    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if ("www.onthisday.com".equals(Uri.parse(url).getHost())) {
                // This is my website, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }
}