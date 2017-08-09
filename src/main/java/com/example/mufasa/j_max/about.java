package com.example.mufasa.j_max;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class about extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        webView=(WebView) findViewById(R.id.webView);

        webView.loadUrl("file:///android_asset/about.html");

    }
}
