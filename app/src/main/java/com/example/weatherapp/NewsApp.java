package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class NewsApp extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem mhome, msciene, mtech, msport, mhealth, mentertainment;
    PagerAdapter pagerAdapter;
    Toolbar mtoolbar;
    ViewPager viewPager;
    ImageView back;
//    String first = "appear";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_app);
        overridePendingTransition(R.anim.side_in_right, R.anim.side_out_left);

        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        Anhxa();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(NewsApp.this, MainActivity.class);
//                intent.putExtra("check", first);
//                startActivity(intent);
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
        mhome           = findViewById(R.id.home);
        msciene         = findViewById(R.id.science);
        mtech           = findViewById(R.id.technology);
        msport          = findViewById(R.id.sports);
        mentertainment  = findViewById(R.id.entertainment);
        mhealth         = findViewById(R.id.health);
        viewPager       = findViewById(R.id.fragmentcontainer);
        tabLayout       = findViewById(R.id.include);
        back            = findViewById(R.id.back);
    }
}