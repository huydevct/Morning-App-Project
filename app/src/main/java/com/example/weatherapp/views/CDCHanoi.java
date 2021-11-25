package com.example.weatherapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weatherapp.R;

public class CDCHanoi extends AppCompatActivity {
    WebView webViewCDC;
    ImageView imgBackCDC;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdchanoi);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        imgBackCDC = findViewById(R.id.imageViewBackCDCHanoi);
        webViewCDC = findViewById(R.id.webViewCDCHanoi);

        imgBackCDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        String url = intent.getStringExtra("linkCDC");

        webViewCDC.loadUrl(url);
        webViewCDC.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webViewCDC.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);

    }
}