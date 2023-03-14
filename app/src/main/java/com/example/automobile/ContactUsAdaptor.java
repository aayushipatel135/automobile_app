package com.example.automobile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ContactUsAdaptor extends RecyclerView.Adapter<ContactUsAdaptor.MyViewHolder>{

    Context context;
    ArrayList<ContactUsData> list;

    public ContactUsAdaptor(Context context,ArrayList<ContactUsData> list){
        this.context = context;
        this.list = list;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displaycontact,parent,false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        ContactUsData contactUsData = list.get(position);
        holder.Contactno1.setText(contactUsData.getContactno1());
        holder.Contactno2.setText(contactUsData.getContactno2());
        holder.Email.setText(contactUsData.getEmail());
        holder.Location.setText(contactUsData.getLocation());
        holder.WorkingHours.setText(contactUsData.getWorkingHours());

        holder.Contactno1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact1 = "tel:" + "";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                contact1 = contact1 + list.get(position).getContactno1();
                callIntent.setData(Uri.parse(contact1));
                context.startActivity(callIntent);
            }
        });
        holder.Contactno2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact2 = "tel:" + "";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                contact2 = contact2 + list.get(position).getContactno2();
                callIntent.setData(Uri.parse(contact2));
                context.startActivity(callIntent);
            }
        });
        holder.Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = "mailto:" + "";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                Email = Email + list.get(position).getEmail();
                intent.setData(Uri.parse(Email));
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Email,Location,WorkingHours,Contactno1,Contactno2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Email = itemView.findViewById(R.id.email);
            Location = itemView.findViewById(R.id.location);
            WorkingHours = itemView.findViewById(R.id.workinghours);
            Contactno1 = itemView.findViewById(R.id.contactno1);
            Contactno2 = itemView.findViewById(R.id.contactno2);

        }
    }
}
