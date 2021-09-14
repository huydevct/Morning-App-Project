package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WelComeActivity extends AppCompatActivity {
    Button btnStart;
    ImageView imgWeather, imgNews, imgToDo, imgCovid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);

        btnStart = findViewById(R.id.buttonStart);
        imgCovid = findViewById(R.id.imageViewCovid);
        imgWeather = findViewById(R.id.imageViewWeather);
        imgNews = findViewById(R.id.imageViewNews);
        imgToDo = findViewById(R.id.imageViewTodo);

        imgToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelComeActivity.this, ToDoActivity.class));
            }
        });

        imgNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelComeActivity.this, TinTucTongHop.class));
            }
        });

        imgWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelComeActivity.this, WeatherApp.class));
            }
        });

        imgCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelComeActivity.this, Covid19Activity.class));
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelComeActivity.this, MainActivity.class));
            }
        });
    }
}