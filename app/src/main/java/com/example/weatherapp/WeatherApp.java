package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherApp extends AppCompatActivity {
    AutoCompleteTextView edtSearch;
    Button btnSearch, btnChangeActivity;
    TextView txtCity, txtCountry, txtTemp, txtStatus, txtCloud, txtDay, txtWind, txtHumidity;
    ImageView imgIcon;
    String City = "";

    // hardcode @@
    private final String [] Citys = {"Ha Noi", "Ho Chi Minh City", "Sai Gon", "London", "France", "America", };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_app);

        Anhxa();
        GetCurrentWeatherData("Hanoi");

        // Auto complete Text
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Citys);
        edtSearch.setAdapter(adapter);
        edtSearch.setThreshold(1);
        edtSearch.setDropDownHeight(400);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = edtSearch.getText().toString();
                city = FixString(city);
                if(city.equals("")){
                    City = "Hanoi";
                    GetCurrentWeatherData(City);
                }else {
                    City = city;
                    GetCurrentWeatherData(City);
                }
            }
        });

        btnChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = edtSearch.getText().toString();
                Intent intent = new Intent(WeatherApp.this, WeatherNextDay.class);
                intent.putExtra("city", city);
                startActivity(intent);
            }
        });
    }

    // fix string tu Ha Noi -> Hanoi
    public static String FixString(String string){
        String string1[] = string.split("\\s");
        StringBuilder result = new StringBuilder();
        string1[0].toUpperCase();
        for (int i=0; i<string1.length; i++){
            result.append(string1[i]);
        }
        return result.toString();
    }

    private void GetCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherApp.this);
        String API_KEY = "71d845b420f0244f5f52c3440f450c9a";
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+data+"&appid="+ API_KEY +"&units=metric";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //tạo json object để chứa tất cả json
                            JSONObject jsonObject = new JSONObject(response);

                            // lấy tên thành phố
                            String name = jsonObject.getString("name");
                            txtCity.setText("Thành Phố: " + name);

                            // định dạng lại và setText cho Ngày
                            String day = jsonObject.getString("dt");
                            long lday = Long.valueOf(day);
                            Date date = new Date(lday*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
                            String Day = simpleDateFormat.format(date);
                            txtDay.setText(Day);


                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);

                            // lấy dữ liệu json Status và Icon
                            //Status
                            String status = jsonObjectWeather.getString("main");
                            txtStatus.setText(status);
                            //Icon
                            String icon = jsonObjectWeather.getString("icon");
                            String url = "http://openweathermap.org/img/wn/"+icon+"@2x.png";

                            Glide.with(getApplicationContext())
                                    .load(url)
                                    .into(imgIcon);//error not yet fix

                            JSONObject jsonObject1Main = jsonObject.getJSONObject("main");
                            // lấy và setText Temparature
                            String temp = jsonObject1Main.getString("temp");
                            Double tempD = Double.valueOf(temp);
                            String Temp = String.valueOf(tempD.intValue());
                            txtTemp.setText(Temp+"°C");

                            // lấy và setText Humidity
                            String humidity = jsonObject1Main.getString("humidity");
                            txtHumidity.setText(humidity + "%");

                            // lấy và setText wind
                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String wind = jsonObjectWind.getString("speed");
                            txtWind.setText(wind+"m/s");

                            // lấy và setText Clouds
                            JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                            String  clouds = jsonObjectCloud.getString("all");
                            txtCloud.setText(clouds + "%");

                            // lấy và setText Country
                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String country = jsonObjectSys.getString("country");
                            txtCountry.setText("Quốc Gia: "+country);

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
    private void Anhxa() {
        edtSearch           = findViewById(R.id.editTextSearch);
        btnSearch           = findViewById(R.id.buttonSearch);
        btnChangeActivity   = findViewById(R.id.buttonChangeActivity);
        txtCity             = findViewById(R.id.textViewCity);
        txtCloud            = findViewById(R.id.textViewCloud);
        txtDay              = findViewById(R.id.textViewDay);
        txtHumidity         = findViewById(R.id.textViewHumidity);
        txtStatus           = findViewById(R.id.textViewStatusMain);
        txtWind             = findViewById(R.id.textViewWind);
        txtTemp             = findViewById(R.id.textViewTemp);
        txtCountry          = findViewById(R.id.textViewCountry);
        imgIcon             = findViewById(R.id.imageViewIcon);
    }
}