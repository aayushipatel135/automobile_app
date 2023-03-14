package com.example.automobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Company extends AppCompatActivity {


    DrawerLayout drawerLayout;

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    RecyclerView recyclerView;
    DatabaseReference database;
    CompanyAdaptor companyAdaptor;
    ArrayList<ProductData> list;


    RecyclerView recyclerViewproduct1;
    DatabaseReference database1;
    SideMenuAdaptor sideMenuAdaptor;
    ArrayList<ProductData> listproduct1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);





        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.nav_bottomview);



        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new
                ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();




        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(Company.this,MainActivity.class));
                        break;
                    case R.id.category:
                        startActivity(new Intent(Company.this,AutoParts.class));
                        break;
                    case R.id.search:
                        startActivity(new Intent(Company.this, Search.class));
                        break;
                    case R.id.company:
                        break;
                    case R.id.account:
                        startActivity(new Intent(Company.this, AccountLogin.class));
                        break;


                }
                return true;
            }
        });



        recyclerView = findViewById(R.id.companies);
        database = FirebaseDatabase.getInstance().getReference("Product");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        list = new ArrayList<>();
        companyAdaptor = new CompanyAdaptor(this,list);
        recyclerView.setAdapter(companyAdaptor);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ProductData productData = dataSnapshot.getValue(ProductData.class);
                    list.add(productData);
                }
                companyAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        recyclerViewproduct1 = findViewById(R.id.category1);
        database1 = FirebaseDatabase.getInstance().getReference("Product");
        recyclerViewproduct1.setHasFixedSize(true);
        recyclerViewproduct1.setLayoutManager(new LinearLayoutManager(this));


        listproduct1 = new ArrayList<>();
        sideMenuAdaptor = new SideMenuAdaptor(this,listproduct1);
        recyclerViewproduct1.setAdapter(sideMenuAdaptor);


        database1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ProductData productData = dataSnapshot.getValue(ProductData.class);
                    listproduct1.add(productData);
                }
                sideMenuAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });










    }
}