package com.example.automobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;



import java.util.ArrayList;

public class AboutUsParagraphAdaptor extends  RecyclerView.Adapter<AboutUsParagraphAdaptor.MyViewHolder>{

    Context context;
    ArrayList<AboutUsData> list;

    public AboutUsParagraphAdaptor(Context context,ArrayList<AboutUsData> list){
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displayaboutusparagraph,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        AboutUsData aboutUsData = list.get(position);
        holder.paragraph.setText(aboutUsData.getParagraph());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView paragraph;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            paragraph = itemView.findViewById(R.id.paragraph);

        }
    }
}
