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

public class Covid19Activity extends AppCompatActivity {
    private final String urlVN = "https://disease.sh/v3/covid-19/countries/vn";
    private final String urlGlobal = "https://disease.sh/v3/covid-19/all";
    private final String urlVacVN = "https://disease.sh/v3/covid-19/vaccine/coverage/countries/vn?lastdays=1";

    TextView txtConfirm, txtRecover, txtDeath, txtVaccine, txtQR;
    Button btnCall, btnSMS;
    LinearLayout linearMenu;
    ImageView imgFlag, imgDown, imgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid19);

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

    private void fetchData(String url) {
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            txtConfirm.setText(jsonObject.getString("cases"));
                            txtRecover.setText(jsonObject.getString("recovered"));
                            txtDeath.setText(jsonObject.getString("deaths"));

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

                            txtVaccine.setText(vac);
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