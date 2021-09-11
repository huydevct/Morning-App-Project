package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnTienIch, btnSearch;
    EditText edtSearch;
    WebView webViewSearch;
    ImageView imgNext, imgBack, imgReload;
    String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();

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
                }else{
                    URL = url;
                    webViewSearch.loadUrl("https://"+url);
                    edtSearch.setText(webViewSearch.getUrl());
                }

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

        btnTienIch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMenu();
            }
        });

        WebSettings webSettings = webViewSearch.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
    }
    private void Anhxa(){
        btnTienIch = findViewById(R.id.buttonTienIch);
        btnSearch  = findViewById(R.id.buttonSearch);
        edtSearch  = findViewById(R.id.editTextTimKiem);
        webViewSearch = findViewById(R.id.webViewSearch);
        imgBack = findViewById(R.id.imageViewBack);
        imgNext = findViewById(R.id.imageViewNext);
        imgReload  = findViewById(R.id.imageViewReload);
    }
    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnTienIch);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menuWeather:
                        startActivity(new Intent(MainActivity.this, WeatherApp.class));
                        break;
                    case R.id.menuTinTuc:
                        startActivity(new Intent(MainActivity.this, TinTucTongHop.class));
                        break;
                }

                return false;
            }
        });
        popupMenu.show();
    }
}