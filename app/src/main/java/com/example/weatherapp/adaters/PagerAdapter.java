package com.example.weatherapp.adaters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.weatherapp.fragments.ScieneFragment;
import com.example.weatherapp.fragments.SportsFragment;
import com.example.weatherapp.fragments.TechFragment;
import com.example.weatherapp.fragments.EntertainmentFragment;
import com.example.weatherapp.fragments.HealthFragment;
import com.example.weatherapp.fragments.HomeFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new SportsFragment();
            case 2:
                return new HealthFragment();
            case 3:
                return new ScieneFragment();
            case 4:
                return new EntertainmentFragment();
            case 5:
                return new TechFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
