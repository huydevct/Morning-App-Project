package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class CDCHanoi extends AppCompatActivity {
    WebView webViewCDC;
    ImageView imgBackCDC, imgPrev, imgNext, imgReload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdchanoi);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        imgBackCDC = findViewById(R.id.imageViewBackCDCHanoi);
        webViewCDC = findViewById(R.id.webViewCDCHanoi);
        imgPrev    = findViewById(R.id.imageViewPrevCDC);
        imgNext    = findViewById(R.id.imageViewNextCDC);
        imgReload  = findViewById(R.id.imageViewReloadCDC);

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

        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webViewCDC.canGoBack()){
                    webViewCDC.goBack();
                    Toast.makeText(CDCHanoi.this, "Trang trước...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CDCHanoi.this, "Không Có Dữ Liệu Trang Trước Để Load!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webViewCDC.canGoForward()){
                    webViewCDC.goForward();
                    Toast.makeText(CDCHanoi.this, "Trang sau...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CDCHanoi.this, "Không Có Dữ Liệu Trang Sau Để Load!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webViewCDC.reload();
            }
        });

    }
}