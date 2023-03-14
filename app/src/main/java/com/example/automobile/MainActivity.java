package com.example.automobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.*;


import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ValueEventListener;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Member;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity  {

    DrawerLayout drawerLayout;

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;






    HomeAdaptor homeAdaptor;
    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<HomeData> list;
    LinearLayoutManager layoutManager;







    RecyclerView recyclerViewproduct;
    DatabaseReference database;
    ProductAdaptor productAdaptor;
    ArrayList<ProductData> listproduct;
    ProductAdaptor productAdaptor1;
    SearchView searchView;
    RecyclerView recyclerViewSearch;




    RecyclerView recyclerViewcontact;
    DatabaseReference databasereferencecontact;
    ContactUsAdaptor contactUsAdaptor;
    ArrayList<ContactUsData> listcontact;





    RecyclerView recyclerViewproduct1;
    DatabaseReference database1;
    SideMenuAdaptor sideMenuAdaptor;
    ArrayList<ProductData> listproduct1;



    AboutUsImageAdaptor aboutUsImageAdaptor;
    RecyclerView recyclerViewaboutusimages;
    DatabaseReference databaseReferenceaboutusimages;
    ArrayList<AboutUsData> listaboutusimages;



    AboutUsParagraphAdaptor aboutUsParagraphAdaptor;
    RecyclerView recyclerViewaboutusparagraph;
    DatabaseReference databaseReferenceaboutusparagraph;
    ArrayList<AboutUsData> listaboutusparagraph;





    CircleImageView whatsapp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        break;
                    case R.id.category:
                        startActivity(new Intent(MainActivity.this,AutoParts.class));
                        break;
                    case R.id.search:
                        startActivity(new Intent(MainActivity.this, Search.class));
                        break;
                    case R.id.company:
                        startActivity(new Intent(MainActivity.this,Company.class));
                        break;
                    case R.id.account:
                        startActivity(new Intent(MainActivity.this, AccountLogin.class));
                        break;


                }
                return true;
            }
        });


        recyclerView  = findViewById(R.id.images);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        recyclerView.setLayoutManager(layoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Home");


        list = new ArrayList<>();
        homeAdaptor = new HomeAdaptor(this,list);
        recyclerView.setAdapter(homeAdaptor);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    HomeData homeData = datasnapshot.getValue(HomeData.class);
                    list.add(homeData);
                }
                homeAdaptor.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(layoutManager.findLastCompletelyVisibleItemPosition() < (homeAdaptor.getItemCount() - 1))
                {
                    layoutManager.smoothScrollToPosition(recyclerView,new RecyclerView.State(),layoutManager.findLastCompletelyVisibleItemPosition() +1);
                }
                else if(layoutManager.findLastCompletelyVisibleItemPosition() > (homeAdaptor.getItemCount() - 1))
                {
                    layoutManager.smoothScrollToPosition(recyclerView,new RecyclerView.State(),0);
                }
            }
        },0,3000);








        recyclerViewproduct = findViewById(R.id.category);
        database = FirebaseDatabase.getInstance().getReference("Product");
        recyclerViewproduct.setHasFixedSize(true);
        recyclerViewproduct.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));


        listproduct = new ArrayList<>();
        productAdaptor = new ProductAdaptor(this,listproduct);
        recyclerViewproduct.setAdapter(productAdaptor);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ProductData productData = dataSnapshot.getValue(ProductData.class);
                    listproduct.add(productData);
                }
                productAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerViewSearch = findViewById(R.id.searchcategory);

        searchView = findViewById(R.id.searchhome);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Search.class));
            }
        });
      /*  searchView.clearFocus();
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
        });*/


        recyclerViewcontact = findViewById(R.id.contacts);
        databasereferencecontact = FirebaseDatabase.getInstance().getReference("Contact");
        recyclerViewcontact.setHasFixedSize(true);
        recyclerViewcontact.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));


        listcontact = new ArrayList<>();
        contactUsAdaptor = new ContactUsAdaptor(this,listcontact);
        recyclerViewcontact.setAdapter(contactUsAdaptor);


        databasereferencecontact.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ContactUsData contactUsData = dataSnapshot.getValue(ContactUsData.class);
                    listcontact.add(contactUsData);
                }
                contactUsAdaptor.notifyDataSetChanged();
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



        recyclerViewaboutusimages  = findViewById(R.id.aboutusimages);
        recyclerViewaboutusimages.setHasFixedSize(true);
        recyclerViewaboutusimages.setLayoutManager(new LinearLayoutManager(this));

        databaseReferenceaboutusimages = FirebaseDatabase.getInstance().getReference("AboutUs");

        listaboutusimages = new ArrayList<>();
        aboutUsImageAdaptor = new AboutUsImageAdaptor(this,listaboutusimages);
        recyclerViewaboutusimages.setAdapter(aboutUsImageAdaptor);

        databaseReferenceaboutusimages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.child("images").getChildren()) {
                    AboutUsData aboutUsData = datasnapshot.getValue(AboutUsData.class);
                    listaboutusimages.add(aboutUsData);
                }
                aboutUsImageAdaptor.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









        recyclerViewaboutusparagraph  = findViewById(R.id.aboutusparagraph);
        recyclerViewaboutusparagraph.setHasFixedSize(true);
        recyclerViewaboutusparagraph.setLayoutManager(new LinearLayoutManager(this));

        databaseReferenceaboutusparagraph = FirebaseDatabase.getInstance().getReference("AboutUs");

        listaboutusparagraph = new ArrayList<>();
        aboutUsParagraphAdaptor = new AboutUsParagraphAdaptor(this,listaboutusparagraph);
        recyclerViewaboutusparagraph.setAdapter(aboutUsParagraphAdaptor);

        databaseReferenceaboutusparagraph.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.child("description").getChildren()) {
                    AboutUsData aboutUsData = datasnapshot.getValue(AboutUsData.class);
                    listaboutusparagraph.add(aboutUsData);
                }
                aboutUsParagraphAdaptor.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });










        whatsapp = findViewById(R.id.whatsapp);

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   /*
                    String url = "https://api.whatsapp.com/send?phone="+"+91 9409399036";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.whatsapp");
                    intent.setData(Uri.parse(url));*/
                String message = "Hello";
                Intent intent = new Intent(Intent.ACTION_VIEW);    // setting action

                // setting data url, if we not catch the exception then it shows an error
                try {
                    String url = "https://api.whatsapp.com/send?phone=+91 9409399036" + "&text=" + URLEncoder.encode(message, "UTF-8");
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
                catch(UnsupportedEncodingException e){
                    Log.d("notSupport", "thrown by encoder");
                }




            }
        });











    }




    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }




    private void filter(String newText) {
        ArrayList<ProductData> filteredList;
        recyclerViewSearch.setHasFixedSize(true);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this));
        filteredList = new ArrayList<>();
        productAdaptor1 = new ProductAdaptor(this,filteredList);
        recyclerViewSearch.setAdapter(productAdaptor1);
        for(ProductData productData:listproduct)
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