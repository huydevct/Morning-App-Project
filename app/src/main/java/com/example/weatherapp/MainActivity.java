package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    ImageView imgWeather, imgNews, imgCovid, imgTodo;
//    String first = "appear";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        Anhxa();

//        Intent intent = getIntent();
//        String firstNews = intent.getStringExtra("check");
//        Log.v("first", firstNews);
//        welcome(first);

        Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();

        imgNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewsApp.class));
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

    }

//    private void welcome(String first){
//        if (first.equals("appear")){
//            Toast.makeText(MainActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
//        }else if(first.equals("hide")){
//            return;
//        }else{
//            first = "hide";
//        }
//    }

    private void Anhxa(){
        imgWeather      = findViewById(R.id.imageButtonWeather);
        imgTodo         = findViewById(R.id.imageButtonTodo);
        imgCovid        = findViewById(R.id.imageButtonCovid);
        imgNews         = findViewById(R.id.imageButtonNews);
    }

}