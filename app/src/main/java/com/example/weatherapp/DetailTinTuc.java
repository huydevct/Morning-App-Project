package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailTinTuc extends AppCompatActivity {
    WebView webView;
    ImageView imgBack, imgNext, imgPrev, imgReload;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tin_tuc);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        imgBack = findViewById(R.id.imageViewBackDetail);
        webView = findViewById(R.id.webViewTinTuc);
        txtTitle = findViewById(R.id.textViewTitle);
        imgPrev = findViewById(R.id.imageViewPrevTT);
        imgNext = findViewById(R.id.imageViewNextTT);
        imgReload = findViewById(R.id.imageViewReloadTT);

        Intent intent = getIntent();
        String link = intent.getStringExtra("linktintuc");
        String title = intent.getStringExtra("title");

        webView.loadUrl(link);
        txtTitle.setText(title);

        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);


        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webView.canGoBack()){
                    webView.goBack();
                    Toast.makeText(DetailTinTuc.this, "Trang trước...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DetailTinTuc.this, "Không Có Dữ Liệu Trang Trước Để Load!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webView.canGoForward()){
                    webView.goForward();
                    Toast.makeText(DetailTinTuc.this, "Trang sau...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DetailTinTuc.this, "Không Có Dữ Liệu Trang Sau Để Load!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.reload();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}