package com.example.automobile;

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

import java.util.ArrayList;

public class AutoPartsAdaptor extends RecyclerView.Adapter<AutoPartsAdaptor.MyViewHolder> {

    Context context;
    ArrayList<ProductData> list;

    public AutoPartsAdaptor(Context context,ArrayList<ProductData> list){
        this.context = context;
        this.list = list;
    }






    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displayautoparts,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ProductData productData = list.get(position);
        holder.nameautoparts.setText(productData.getName());
        Glide.with(context).load(list.get(position).getLogo()).into(holder.logoautoparts);

        holder.display_autoparts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context,ProductOnClick.class);
                intent.putExtra("logourl",list.get(position).getLogo());
                intent.putExtra("name",list.get(position).getName());
                intent.putExtra("products",list.get(position).getProducts());
                intent.putExtra("description",list.get(position).getDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameautoparts;
        ImageView logoautoparts;
        CardView display_autoparts;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            logoautoparts = itemView.findViewById(R.id.logoautoparts);
            nameautoparts = itemView.findViewById(R.id.nameautoparts);
            display_autoparts = itemView.findViewById(R.id.display_autoparts);
        }
    }

}
