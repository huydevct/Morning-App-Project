package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TinTucTongHop extends AppCompatActivity {
    ImageView imgBack;
    TextView txtTheGioi, txtThoiSu, txtMoiNhat, txtNoiBat, txtTheThao, txtGiaiTri, txtKhoaHoc, txtKinhDoanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc_tong_hop);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        Anhxa();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TinTucTongHop.this, MainActivity.class));
            }
        });

        txtTheGioi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TinTucTongHop.this, TinTuc.class));
            }
        });

        txtThoiSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TinTucTongHop.this, ThoiSu.class));
            }
        });

        txtMoiNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TinTucTongHop.this, MoiNhat.class));
            }
        });

        txtNoiBat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TinTucTongHop.this, NoiBat.class));
            }
        });

        txtTheThao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TinTucTongHop.this, TheThao.class));
            }
        });

        txtGiaiTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TinTucTongHop.this, GiaiTri.class));
            }
        });

        txtKhoaHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TinTucTongHop.this, KhoaHoc.class));
            }
        });

        txtKinhDoanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TinTucTongHop.this, KinhDoanh.class));
            }
        });
    }

    private void Anhxa() {
        imgBack    = findViewById(R.id.imageViewBackMain);
        txtTheGioi = findViewById(R.id.textViewTheGioi);
        txtThoiSu = findViewById(R.id.textViewThoiSu);
        txtMoiNhat = findViewById(R.id.textViewTinMoiNhat);
        txtNoiBat = findViewById(R.id.textViewTinNoiBat);
        txtTheThao = findViewById(R.id.textViewTheThao);
        txtGiaiTri = findViewById(R.id.textViewGiaiTri);
        txtKhoaHoc = findViewById(R.id.textViewKhoaHoc);
        txtKinhDoanh = findViewById(R.id.textViewKinhDoanh);
    }
}