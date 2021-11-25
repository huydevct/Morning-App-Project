package com.example.weatherapp.adaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.models.ThoiTiet;
import com.example.weatherapp.views.WeatherApp;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ThoiTiet> thoiTietArrayList;

    public CustomAdapter(Context context, ArrayList<ThoiTiet> thoiTietArrayList) {
        this.context = context;
        this.thoiTietArrayList = thoiTietArrayList;
    }

    @Override
    public int getCount() {
        return thoiTietArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return thoiTietArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dong_list_view, null);

        ThoiTiet thoiTiet = thoiTietArrayList.get(i);

        TextView txtDay     = view.findViewById(R.id.textViewTime);
        TextView txtStatus  = view.findViewById(R.id.textViewStatus);
        TextView txtMaxTemp = view.findViewById(R.id.textViewMaxTemp);
        TextView txtMinTemp = view.findViewById(R.id.textViewMinTemp);
        ImageView imgStatus = view.findViewById(R.id.imageViewStatus);

        WeatherApp.GetIconImage(thoiTiet.Image, imgStatus);
        txtDay.setText(thoiTiet.Day);
        txtStatus.setText(thoiTiet.Status);
        txtMaxTemp.setText(thoiTiet.MaxTemp + "°C");
        txtMinTemp.setText(thoiTiet.MinTemp + "°C");

        //animation
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_list);
        view.startAnimation(animation);

        return view;
    }


}
