package com.example.automobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.automobile.HomeData;
import com.example.automobile.ProductData;
import com.example.automobile.R;

import java.util.ArrayList;

class HomeAdaptor extends RecyclerView.Adapter<HomeAdaptor.MyViewHolder> {


    Context context;
    ArrayList<HomeData> list;

    public HomeAdaptor(Context context,ArrayList<HomeData> list){
        this.context = context;
        this.list = list;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_image_display,parent,false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        //Glide.with(context).load(list.get(position).getImgurl()).into((ImageView) holder.itemView);
        Glide.with(context).load(list.get(position).getImgurl()).into(holder.imgurl);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imgurl;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgurl = itemView.findViewById(R.id.image1);

        }
    }
}
