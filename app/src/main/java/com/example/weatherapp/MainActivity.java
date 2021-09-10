package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnWeather, btnTinTuc, btnSearch;
    EditText edtSearch;
    WebView webViewSearch;
    ImageView imgNext, imgBack, imgReload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();

        webViewSearch.setWebViewClient(new WebViewClient());
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = edtSearch.getText().toString().trim();
                webViewSearch.loadUrl("https://"+url);
                edtSearch.setText(webViewSearch.getUrl());
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webViewSearch.canGoBack()){
                    webViewSearch.goBack();
                    edtSearch.setText(webViewSearch.getUrl());
                    Toast.makeText(MainActivity.this, "Trang trước...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Không Có Dữ Liệu Trang Trước Để Load!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(webViewSearch.canGoForward()){
                    webViewSearch.goForward();
                    edtSearch.setText(webViewSearch.getUrl());
                    Toast.makeText(MainActivity.this, "Trang sau...", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Không Có Dữ Liệu Trang Sau Để Load!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webViewSearch.reload();
                edtSearch.setText(webViewSearch.getUrl());
            }
        });

        WebSettings webSettings = webViewSearch.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);

        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeatherApp.class);
                startActivity(intent);
            }
        });

        btnTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TinTuc.class));
            }
        });
    }
    private void Anhxa(){
        btnWeather = findViewById(R.id.buttonWeather);
        btnTinTuc  = findViewById(R.id.buttonTinTuc);
        btnSearch  = findViewById(R.id.buttonSearch);
        edtSearch  = findViewById(R.id.editTextTimKiem);
        webViewSearch = findViewById(R.id.webViewSearch);
        imgBack = findViewById(R.id.imageViewBack);
        imgNext = findViewById(R.id.imageViewNext);
        imgReload  = findViewById(R.id.imageViewReload);
    }
}