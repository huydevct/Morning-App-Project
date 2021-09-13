package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class Covid19Activity extends AppCompatActivity {
    private final String urlCDC = "https://covidmaps.hanoi.gov.vn/";
    private final String urlVN = "https://disease.sh/v3/covid-19/countries/vn";
    private final String urlGlobal = "https://disease.sh/v3/covid-19/all";
    private final String urlVacVN = "https://disease.sh/v3/covid-19/vaccine/coverage/countries/vn?lastdays=1";

    TextView txtConfirm, txtRecover, txtDeath, txtVaccine, txtQR;
    Button btnCall, btnSMS;
    LinearLayout linearMenu;
    ImageView imgFlag, imgDown, imgBack, imgCDC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid19);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        Anhxa();

        fetchData(urlVN);
        fetchVaccineData();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowMenu();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Covid19Activity.this, Call.class));
            }
        });

        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Covid19Activity.this, SMS.class));
            }
        });

        txtQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Covid19Activity.this, QR.class));
            }
        });

        imgCDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Covid19Activity.this, CDCHanoi.class);
                intent.putExtra("linkCDC", urlCDC);
                startActivity(intent);
            }
        });
    }

    private void ShowMenu() {
        PopupMenu popupMenu = new PopupMenu(Covid19Activity.this, imgDown);
        popupMenu.getMenuInflater().inflate(R.menu.menu_covid, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menuVietNam:
                        imgFlag.setImageResource(R.drawable.vietnam_icon);
                        fetchData(urlVN);
                        fetchVaccineData();
                        break;
                    case R.id.menuGlobal:
                        imgFlag.setImageResource(R.drawable.global_icon);
                        fetchData(urlGlobal);
                        fetchVaccineData();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private String formatNum(String num){
        long numl = Long.parseLong(num);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(numl);
    }

    private void fetchData(String url) {
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            txtConfirm.setText(formatNum(jsonObject.getString("cases")));
                            txtRecover.setText(formatNum(jsonObject.getString("recovered")));
                            txtDeath.setText(formatNum(jsonObject.getString("deaths")));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Covid19Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(Covid19Activity.this);
        requestQueue.add(request);
    }

    private void fetchVaccineData(){
        StringRequest request = new StringRequest(Request.Method.GET, urlVacVN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONObject jsonObjectTimeline = jsonObject.getJSONObject("timeline");
                            String vac = jsonObjectTimeline.getString("9/12/21");
                            String vacFormat = formatNum(vac);

                            txtVaccine.setText(vacFormat);
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

        RequestQueue requestQueue = Volley.newRequestQueue(Covid19Activity.this);
        requestQueue.add(request);
    }

    private void Anhxa() {
        imgCDC     = findViewById(R.id.imageViewCDC);
        txtQR      = findViewById(R.id.textViewQR);
        imgBack    = findViewById(R.id.imageViewBackToMain);
        txtConfirm = findViewById(R.id.textViewConfirmCase);
        txtDeath   = findViewById(R.id.textViewDeath);
        txtRecover = findViewById(R.id.textViewRecovered);
        txtVaccine = findViewById(R.id.textViewVaccine);
        btnCall    = findViewById(R.id.buttonCall);
        btnSMS     = findViewById(R.id.buttonSMS);
        imgFlag    = findViewById(R.id.imageFlag);
        imgDown    = findViewById(R.id.imageViewDown);
        linearMenu = findViewById(R.id.linearLayoutMenu);
    }
}