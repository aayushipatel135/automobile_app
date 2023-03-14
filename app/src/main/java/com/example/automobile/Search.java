package com.example.automobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {


    DrawerLayout drawerLayout;

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    SearchView searchView;
    ArrayList<ProductData> listsearch;
    ProductAdaptor productAdaptor;
    ProductAdaptor productAdaptor1;
    DatabaseReference database;
    RecyclerView recyclerViewSearch;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);




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
                        startActivity(new Intent(Search.this,MainActivity.class));
                        break;
                    case R.id.category:
                        startActivity(new Intent(Search.this,AutoParts.class));
                        break;
                    case R.id.search:
                        break;
                    case R.id.company:
                        startActivity(new Intent(Search.this,Company.class));
                        break;
                    case R.id.account:
                        startActivity(new Intent(Search.this, AccountLogin.class));
                        break;


                }
                return true;
            }
        });







        database = FirebaseDatabase.getInstance().getReference("Product");
        listsearch = new ArrayList<>();
        productAdaptor = new ProductAdaptor(this,listsearch);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ProductData productData = dataSnapshot.getValue(ProductData.class);
                    listsearch.add(productData);
                }
                productAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });












        recyclerViewSearch = findViewById(R.id.searchcategory);



        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });













    }

    private void filter(String newText) {
        ArrayList<ProductData> filteredList;
        recyclerViewSearch.setHasFixedSize(true);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this));
        filteredList = new ArrayList<>();
        productAdaptor1 = new ProductAdaptor(this,filteredList);
        recyclerViewSearch.setAdapter(productAdaptor1);
        for(ProductData productData:listsearch)
        {
            if(productData.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(productData);
            }
        }
        if(filteredList.isEmpty())
        {
            Toast.makeText(this,"No DATA FOUND" ,Toast.LENGTH_SHORT).show();
        }
        else {
            productAdaptor1.filterList(filteredList);
        }

    }
}