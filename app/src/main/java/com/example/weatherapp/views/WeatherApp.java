package com.example.weatherapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherApp extends AppCompatActivity {
    EditText edtSearch;
    Button btnSearch;
    LinearLayout linearChangeActivity;
    TextView txtCity, txtTemp, txtStatus, txtCloud, txtDay, txtWind, txtHumidity, txtMinTempMain, txtMaxTempMain, txtSunrise, txtSunset;
    ImageView imgIcon, imagBack;
    String City = "";
    String cityChange = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_app);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        Anhxa();
        GetCurrentWeatherData("Hanoi");

        imagBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeatherApp.this, MainActivity.class));
            }
        });

        btnSearch.setOnClickListener(view -> {
            String city = edtSearch.getText().toString();
            city = FixString(city);
            if(city.equals("")){
                City = "Hanoi";
                GetCurrentWeatherData(City);
            }else {
                City = city;
                GetCurrentWeatherData(City);
            }
            cityChange = city;
            edtSearch.setText("");
        });

        linearChangeActivity.setOnClickListener(view -> {
//            String city = edtSearch.getText().toString();
            Intent intent = new Intent(WeatherApp.this, WeatherNextDay.class);
            intent.putExtra("city", cityChange);
            startActivity(intent);
        });
    }

    // fix string tu Ha Noi -> Hanoi
    public static String FixString(String string){
        String[] string1 = string.split("\\s");
        StringBuilder result = new StringBuilder();
        string1[0].toUpperCase();
        for (String s : string1) {
            result.append(s);
        }
        return result.toString();
    }

    private void GetCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherApp.this);
        String API_KEY = "71d845b420f0244f5f52c3440f450c9a";
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+data+"&appid="+ API_KEY +"&units=metric";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        try {
                            //tạo json object để chứa tất cả json
                            JSONObject jsonObject = new JSONObject(response);

                            // lấy tên thành phố
                            String name = jsonObject.getString("name");
                            txtCity.setText(name);

                            // định dạng lại và setText cho Ngày
                            String day = jsonObject.getString("dt");
                            long lDay = Long.valueOf(day);
                            Date date = new Date(lDay*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
                            String Day = simpleDateFormat.format(date);
                            txtDay.setText(Day);

                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);

                            // lấy dữ liệu json Status và Icon
                            //Status
                            String status = jsonObjectWeather.getString("main");
                            txtStatus.setText(status);

                            //Icon Glide
                            String icon = jsonObjectWeather.getString("icon");
                            GetIconImage(icon, imgIcon);

                            JSONObject jsonObject1Main = jsonObject.getJSONObject("main");
                            // lấy và setText Temparature
                            String temp = jsonObject1Main.getString("temp");
                            Double tempD = Double.valueOf(temp);
                            String Temp = String.valueOf(tempD.intValue());
                            txtTemp.setText(Temp+"°C");

                            //Min Temp
                            String minTemp = jsonObject1Main.getString("temp_min");
                            Double minTempD = Double.valueOf(minTemp);
                            String MinTemp = String.valueOf(minTempD.intValue());
                            txtMinTempMain.setText("Min: " + MinTemp + "°C");

                            //Max Temp
                            String maxTemp = jsonObject1Main.getString("temp_max");
                            Double maxTempD = Double.valueOf(maxTemp);
                            String MaxTemp = String.valueOf(maxTempD.intValue());
                            txtMaxTempMain.setText("Max: " + MaxTemp + "°C");

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

                            // lấy và đổ dữ liệu cho text view sunrise
                            String sunrise = jsonObjectSys.getString("sunrise");
                            long lSunrise = Long.valueOf(sunrise);
                            Date dateSunrise = new Date(lSunrise*1000L);
                            SimpleDateFormat simpleDateFormatSunrise = new SimpleDateFormat("hh:mm a");
                            String Sunrise = simpleDateFormatSunrise.format(dateSunrise);
                            txtSunrise.setText(Sunrise);

                            // lấy và đổ dữ liệu cho text view sunset
                            String sunset = jsonObjectSys.getString("sunset");
                            long lSunset = Long.valueOf(sunset);
                            Date dateSunset = new Date(lSunset*1000L);
                            SimpleDateFormat simpleDateFormatSunset = new SimpleDateFormat("hh:mm a");
                            String Sunset = simpleDateFormatSunset.format(dateSunset);
                            txtSunset.setText(Sunset);

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

    public static void GetIconImage(String icon, ImageView imageView){
        switch (icon){
            case "01d":
                imageView.setImageResource(R.drawable.d01);
                break;
            case "01n":
                imageView.setImageResource(R.drawable.n01);
                break;
            case "02d":
                imageView.setImageResource(R.drawable.d02);
                break;
            case "02n":
                imageView.setImageResource(R.drawable.n02);
                break;
            case "03d":
                imageView.setImageResource(R.drawable.d03);
                break;
            case "03n":
                imageView.setImageResource(R.drawable.n03);
                break;
            case "04d":
                imageView.setImageResource(R.drawable.d04);
                break;
            case "04n":
                imageView.setImageResource(R.drawable.n04);
                break;
            case "09d":
                imageView.setImageResource(R.drawable.d09);
                break;
            case "09n":
                imageView.setImageResource(R.drawable.n09);
                break;
            case "10d":
                imageView.setImageResource(R.drawable.d10);
                break;
            case "10n":
                imageView.setImageResource(R.drawable.n10);
                break;
            case "11d":
                imageView.setImageResource(R.drawable.d11);
                break;
            case "11n":
                imageView.setImageResource(R.drawable.n11);
                break;
            case "13d":
                imageView.setImageResource(R.drawable.d13);
                break;
            case "13n":
                imageView.setImageResource(R.drawable.n13);
                break;
            case "50d":
                imageView.setImageResource(R.drawable.d50);
                break;
            case "50n":
                imageView.setImageResource(R.drawable.n50);
                break;
        }
    }
    private void Anhxa() {
        imagBack            = findViewById(R.id.imageViewBackWeather);
        edtSearch           = findViewById(R.id.editTextSearch);
        btnSearch           = findViewById(R.id.buttonSearch);
        linearChangeActivity   = findViewById(R.id.linearChangeActivity);
        txtCity             = findViewById(R.id.textViewCity);
        txtCloud            = findViewById(R.id.textViewCloud);
        txtDay              = findViewById(R.id.textViewDay);
        txtHumidity         = findViewById(R.id.textViewHumidity);
        txtStatus           = findViewById(R.id.textViewStatusMain);
        txtWind             = findViewById(R.id.textViewWind);
        txtTemp             = findViewById(R.id.textViewTemp);
        txtMinTempMain      = findViewById(R.id.textViewMinTempMain);
        txtMaxTempMain      = findViewById(R.id.textViewMaxTempMain);
        imgIcon             = findViewById(R.id.imageViewIcon);
        txtSunrise          = findViewById(R.id.textViewSunrise);
        txtSunset           = findViewById(R.id.textViewSunset);
    }
}