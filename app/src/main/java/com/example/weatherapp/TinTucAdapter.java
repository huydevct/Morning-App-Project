package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TinTucAdapter extends ArrayAdapter<DocBao> {

    public TinTucAdapter(Context context, int resource, List<DocBao> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.dong_tin_tuc, null);
        }
        DocBao docBao = getItem(position);
        if (docBao != null) {
            // Anh xa + Gan gia tri
            TextView txtTitle = (TextView) view.findViewById(R.id.textViewTinTuc);
            txtTitle.setText(docBao.getTitle());

            ImageView imageView = view.findViewById(R.id.imageViewTinTuc);

            Glide.with(getContext()).load(docBao.getImage()).into(imageView);

        }
        return view;
    }



}
