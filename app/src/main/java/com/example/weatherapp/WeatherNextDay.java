package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherNextDay extends AppCompatActivity {
    private final String API_KEY = "71d845b420f0244f5f52c3440f450c9a";
    String City = "";
    ImageView imgBack;
    TextView txtNameCity;
    ListView lvStatus;
    CustomAdapter adapter;
    ArrayList<ThoiTiet> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_next_day);

        Anhxa();

        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        city = WeatherApp.FixString(city);
        if(city.equals("")){
            City = "Hanoi";
            Get7DaysData(City);
        }else{
            City = city;
            Get7DaysData(City);
        }

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void Anhxa() {
        txtNameCity = findViewById(R.id.textViewNameCity);
        lvStatus    = findViewById(R.id.listViewStatus);
        imgBack     = findViewById(R.id.imageViewBack);
        arrayList   = new ArrayList<ThoiTiet>();
        adapter     = new CustomAdapter(WeatherNextDay.this, arrayList);
        lvStatus.setAdapter(adapter);
    }

    private void Get7DaysData(String data) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+data+"&cnt=7&units=metric&appid="+API_KEY+"";
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherNextDay.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            // setText tên Thành Phố
                            JSONObject jsonObjectCity = jsonObject.getJSONObject("city");
                            String city = jsonObjectCity.getString("name");
                            txtNameCity.setText(city + " 7 ngày tới:");

                            JSONArray jsonArrayList = jsonObject.getJSONArray("list");
                            for (int i=0; i<jsonArrayList.length(); i++){
                                JSONObject jsonObjectList = jsonArrayList.getJSONObject(i);

                                //lấy dữ liệu cho time
                                String day = jsonObjectList.getString("dt");
                                long lday = Long.valueOf(day);
                                Date date = new Date(lday*1000L);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
                                String Day = simpleDateFormat.format(date);

                                JSONObject jsonObjectTemp = jsonObjectList.getJSONObject("main");

                                // lấy dữ liệu cho max temp
                                String maxTemp = jsonObjectTemp.getString("temp_max");
                                Double maxTempD = Double.valueOf(maxTemp);
                                String MaxTemp = String.valueOf(maxTempD.intValue());

                                //lấy dữ liệu cho min temp
                                String minTemp = jsonObjectTemp.getString("temp_min");
                                Double minTempD = Double.valueOf(minTemp);
                                String MinTemp = String.valueOf(minTempD.intValue());

                                //lấy dữ liệu cho icon và status
                                JSONArray jsonArrayWeather = jsonObjectList.getJSONArray("weather");
                                JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                                String status   = jsonObjectWeather.getString("description");
                                String icon     = jsonObjectWeather.getString("icon");

                                arrayList.add(new ThoiTiet(Day, status, icon, MaxTemp, MinTemp));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        requestQueue.add(stringRequest);
    }
}