package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SMS extends AppCompatActivity {
    private static final String LOG_TAG = "AndroidExample";
    EditText edtNumber, edtMessage;
    Button btnSend;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        edtMessage = findViewById(R.id.editTextMessage);
        edtNumber  = findViewById(R.id.editTextNumber);
        btnSend    = findViewById(R.id.buttonSend);
        imgBack    = findViewById(R.id.imageViewBackCovid);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMS_by_Intent_ACTION_SEND();
            }
        });
    }

    private void sendSMS_by_Intent_ACTION_SEND() {
        String number = edtNumber.getText().toString().trim();
        String message = edtMessage.getText().toString();

        Uri uri = Uri.parse("smsto:"+number);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", message);
        try {
            startActivity(intent);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Your sms has failed... " + ex.getMessage(), ex);
            Toast.makeText(SMS.this, "Your sms has failed... " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }


    }
}