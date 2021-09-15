package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton btnSearch;
    EditText edtSearch;
    WebView webViewSearch;
    ImageView imgNext, imgBack, imgReload, imgWeather, imgNews, imgCovid, imgTodo, imgHome;
    String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        Anhxa();

        edtSearch.setShowSoftInputOnFocus(false);
        webViewSearch.setWebViewClient(new WebViewClient());
        String urlMain = edtSearch.getText().toString().trim();
        if(urlMain.equals("")){
            URL = "https://google.com";
            webViewSearch.loadUrl(URL);
            edtSearch.setText(webViewSearch.getUrl());
        }else{
            URL = urlMain;
            webViewSearch.loadUrl("https://"+urlMain);
            edtSearch.setText(webViewSearch.getUrl());
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = edtSearch.getText().toString().trim();
                if(url.equals("https://google.com/")){
                    webViewSearch.loadUrl(url);
                    edtSearch.setText(webViewSearch.getUrl());
                }else if(url.contains("https://")){
                    URL = url;
                    webViewSearch.loadUrl(url);
                    edtSearch.setText(webViewSearch.getUrl());
                }else{
                    URL = url;
                    webViewSearch.loadUrl("https://" + url);
                    edtSearch.setText(webViewSearch.getUrl());
                }

            }
        });

        imgNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TinTucTongHop.class));
            }
        });

        imgWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WeatherApp.class));
            }
        });

        imgTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ToDoActivity.class));
            }
        });

        imgCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Covid19Activity.class));
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

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WelComeActivity.class));
            }
        });

        WebSettings webSettings = webViewSearch.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
    }
    private void Anhxa(){
        imgHome     = findViewById(R.id.imageButtonHome);
        imgWeather  = findViewById(R.id.imageButtonWeather);
        imgTodo     = findViewById(R.id.imageButtonTodo);
        imgCovid    = findViewById(R.id.imageButtonCovid);
        imgNews     = findViewById(R.id.imageButtonNews);
        btnSearch   = findViewById(R.id.buttonSearch);
        edtSearch   = findViewById(R.id.editTextTimKiem);
        webViewSearch = findViewById(R.id.webViewSearch);
        imgBack     = findViewById(R.id.imageViewBack);
        imgNext     = findViewById(R.id.imageViewNext);
        imgReload   = findViewById(R.id.imageViewReload);
    }

}