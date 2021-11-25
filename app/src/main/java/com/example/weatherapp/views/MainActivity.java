package com.example.weatherapp.views;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weatherapp.R;

public class MainActivity extends AppCompatActivity {
    ImageView imgWeather, imgNews, imgCovid, imgTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        Anhxa();

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

    private void Anhxa(){
        imgWeather      = findViewById(R.id.imageButtonWeather);
        imgTodo         = findViewById(R.id.imageButtonTodo);
        imgCovid        = findViewById(R.id.imageButtonCovid);
        imgNews         = findViewById(R.id.imageButtonNews);
    }

}