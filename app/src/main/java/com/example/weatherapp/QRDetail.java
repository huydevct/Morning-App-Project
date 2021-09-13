package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class QRDetail extends AppCompatActivity {
    TextView txtDetail;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrdetail);

        txtDetail = findViewById(R.id.textViewDetail);
        imgBack   = findViewById(R.id.imageViewBackToQR);

        Intent intent = getIntent();
        String detail = intent.getStringExtra("detail");
        txtDetail.setText(detail);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}