package com.example.weatherapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.weatherapp.controllers.PagerAdapter;
import com.example.weatherapp.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class NewsApp extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem home, sciene, tech, sport, health, entertainment;
    PagerAdapter pagerAdapter;
    Toolbar toolbar;
    ViewPager viewPager;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_app);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Anhxa();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 6);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0 ||tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3 ||
                        tab.getPosition() == 4 || tab.getPosition() == 5){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void Anhxa() {
        home           = findViewById(R.id.home);
        sciene         = findViewById(R.id.science);
        tech           = findViewById(R.id.technology);
        sport          = findViewById(R.id.sports);
        entertainment  = findViewById(R.id.entertainment);
        health         = findViewById(R.id.health);
        viewPager       = findViewById(R.id.fragmentcontainer);
        tabLayout       = findViewById(R.id.include);
        back            = findViewById(R.id.back);
    }
}