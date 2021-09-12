package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailTinTuc extends AppCompatActivity {
    WebView webView;
    ImageView imgBack;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tin_tuc);

        imgBack = findViewById(R.id.imageViewBackDetail);
        webView = findViewById(R.id.webViewTinTuc);
        txtTitle = findViewById(R.id.textViewTitle);

        Intent intent = getIntent();
        String link = intent.getStringExtra("linktintuc");
        String title = intent.getStringExtra("title");

        webView.loadUrl(link);

        webView.setWebViewClient(new WebViewClient());

        txtTitle.setText(title);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}