package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ThoiTiet> thoiTietArrayList;
    ImageView imgStatus;

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

        TextView txtDay = view.findViewById(R.id.textViewTime);
        TextView txtStatus = view.findViewById(R.id.textViewStatus);
        TextView txtMaxTemp = view.findViewById(R.id.textViewMaxTemp);
        TextView txtMinTemp = view.findViewById(R.id.textViewMinTemp);
        imgStatus = view.findViewById(R.id.imageViewStatus);

//        Glide.with(context).load("http://openweathermap.org/img/wn/"+thoiTiet.Image+"@2x.png").into(imgStatus);//error
        GetIconImage(thoiTiet.Image);
        txtDay.setText(thoiTiet.Day);
        txtStatus.setText(thoiTiet.Status);
        txtMaxTemp.setText(thoiTiet.MaxTemp + "°C");
        txtMinTemp.setText(thoiTiet.MinTemp + "°C");

        return view;
    }

    // fix error Glide
    // use switch hard code
    public void GetIconImage(String icon){
        switch (icon){
            case "01d":
                imgStatus.setImageResource(R.drawable.d01);
                break;
            case "01n":
                imgStatus.setImageResource(R.drawable.n01);
                break;
            case "02d":
                imgStatus.setImageResource(R.drawable.d02);
                break;
            case "02n":
                imgStatus.setImageResource(R.drawable.n02);
                break;
            case "03d":
                imgStatus.setImageResource(R.drawable.d03);
                break;
            case "03n":
                imgStatus.setImageResource(R.drawable.n03);
                break;
            case "04d":
                imgStatus.setImageResource(R.drawable.d04);
                break;
            case "04n":
                imgStatus.setImageResource(R.drawable.n04);
                break;
            case "09d":
                imgStatus.setImageResource(R.drawable.d09);
                break;
            case "09n":
                imgStatus.setImageResource(R.drawable.n09);
                break;
            case "10d":
                imgStatus.setImageResource(R.drawable.d10);
                break;
            case "10n":
                imgStatus.setImageResource(R.drawable.n10);
                break;
            case "11d":
                imgStatus.setImageResource(R.drawable.d11);
                break;
            case "11n":
                imgStatus.setImageResource(R.drawable.n11);
                break;
            case "13d":
                imgStatus.setImageResource(R.drawable.d13);
                break;
            case "13n":
                imgStatus.setImageResource(R.drawable.n13);
                break;
            case "50d":
                imgStatus.setImageResource(R.drawable.d50);
                break;
            case "50n":
                imgStatus.setImageResource(R.drawable.n50);
                break;
        }
    }
}
