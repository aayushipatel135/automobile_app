package com.example.automobile;
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
import java.util.List;
import java.util.Locale;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.MyViewHolder> {


    Context context;
    ArrayList<ProductData> list;

    public ProductAdaptor(Context context,ArrayList<ProductData> list){
        this.context = context;
        this.list = list;
    }


    public void filterList(ArrayList<ProductData> filterlist) {
        list = filterlist;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displayproducts,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ProductData productData = list.get(position);
        holder.name.setText(productData.getName());
        Glide.with(context).load(list.get(position).getLogo()).into(holder.logo);

        holder.display_products.setOnClickListener(new View.OnClickListener() {
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

        TextView name;
        ImageView logo;
        CardView display_products;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.logo);
            name = itemView.findViewById(R.id.name);
            display_products = itemView.findViewById(R.id.display_products);
        }
    }
}
