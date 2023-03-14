package com.example.automobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductOnClick extends AppCompatActivity {

    ImageView imageView;
    TextView name,description,products;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_on_click);


        imageView = findViewById(R.id.onclickimage);
        name = findViewById(R.id.onclickname);
        description = findViewById(R.id.onclickdescription);
        products = findViewById(R.id.onclickproduct);




        Glide.with(this).load(getIntent().getStringExtra("logourl")).into(imageView);
        name.setText(getIntent().getStringExtra("name"));
        description.setText(getIntent().getStringExtra("description"));
        products.setText(getIntent().getStringExtra("products"));


    }
}