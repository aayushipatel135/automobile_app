package com.example.automobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AccountSignUp extends AppCompatActivity {


    TextView signin;


    RecyclerView recyclerViewproduct1;
    DatabaseReference database1;
    SideMenuAdaptor sideMenuAdaptor;
    ArrayList<ProductData> listproduct1;

    DrawerLayout drawerLayout;

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;



    private Button signupbtn;
    private FirebaseAuth mAuth;
    private EditText Email,Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sign_up);


        signin = findViewById(R.id.gotosignin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountSignUp.this,AccountLogin.class);
                startActivity(intent);
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
                        startActivity(new Intent(AccountSignUp.this, MainActivity.class));
                        break;
                    case R.id.category:
                        startActivity(new Intent(AccountSignUp.this,AutoParts.class));
                        break;
                    case R.id.search:
                        startActivity(new Intent(AccountSignUp.this, Search.class));
                        break;
                    case R.id.company:
                        startActivity(new Intent(AccountSignUp.this,Company.class));
                        break;
                    case R.id.account:
                        break;


                }
                return true;
            }
        });

        signupbtn = findViewById(R.id.registration_btn);
        mAuth = FirebaseAuth.getInstance();
        Email = findViewById(R.id.email_reg);
        Password = findViewById(R.id.passreg);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }

    private void createUser(){
        String email = Email.getText().toString();
        String pass = Password.getText().toString();

        if(TextUtils.isEmpty(email))
        {
            Email.setError("Email can not be empty");
        }
        else if(TextUtils.isEmpty(pass))
        {
            Password.setError("Password can not be empty");
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AccountSignUp.this,"Registration done successfully",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(AccountSignUp.this,"Registration Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}