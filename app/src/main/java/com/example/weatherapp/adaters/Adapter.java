package com.example.weatherapp.adaters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.models.ModelClass;
import com.example.weatherapp.R;
import com.example.weatherapp.views.webView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<ModelClass> modelClasses;

    public Adapter(Context context, ArrayList<ModelClass> modelClasses) {
        this.context = context;
        this.modelClasses = modelClasses;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, webView.class);
                intent.putExtra("url", modelClasses.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.time.setText("Published At:-"+modelClasses.get(position).getPublishedAt());
        holder.author.setText(modelClasses.get(position).getAuthor());
        holder.heading.setText(modelClasses.get(position).getTitle());
        holder.content.setText(modelClasses.get(position).getDescription());
        //Glide lấy ảnh từ internet
        Glide.with(context).load(modelClasses.get(position).getUrlToImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView heading, content, author, time;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading    = itemView.findViewById(R.id.mainheading);
            content    = itemView.findViewById(R.id.content);
            author     = itemView.findViewById(R.id.author);
            time       = itemView.findViewById(R.id.time);
            imageView   = itemView.findViewById(R.id.imageview);
            cardView    = itemView.findViewById(R.id.cardview);
        }
    }
}
