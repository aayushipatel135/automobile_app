package com.example.automobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AccountLogin extends AppCompatActivity {

    TextView signup;


    RecyclerView recyclerViewproduct1;
    DatabaseReference database1;
    SideMenuAdaptor sideMenuAdaptor;
    ArrayList<ProductData> listproduct1;


    DrawerLayout drawerLayout;

    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;




    private Button studentLogInBtn;
    private FirebaseAuth mAuth;
    private EditText Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);



        signup = findViewById(R.id.gotosignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountLogin.this,AccountSignUp.class);
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
                        startActivity(new Intent(AccountLogin.this, MainActivity.class));
                        break;
                    case R.id.category:
                        startActivity(new Intent(AccountLogin.this,AutoParts.class));
                        break;
                    case R.id.search:
                        startActivity(new Intent(AccountLogin.this, Search.class));
                        break;
                    case R.id.company:
                        startActivity(new Intent(AccountLogin.this,Company.class));
                        break;
                    case R.id.account:
                        break;


                }
                return true;
            }
        });


        studentLogInBtn = findViewById(R.id.signin_btn);
        mAuth = FirebaseAuth.getInstance();

        Email = findViewById(R.id.email_reg);
        Password = findViewById(R.id.passreg);
        studentLogInBtn.setOnClickListener((v)->{loginUser();});
    }

    private void loginUser(){

        String email = Email.getText().toString();
        String pass = Password.getText().toString();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            if(!pass.isEmpty()){
                mAuth.signInWithEmailAndPassword(email,pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(AccountLogin.this,"Login Succesfully",Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(SignIn.this, Profile.class));
                                startActivity(new Intent(AccountLogin.this, MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AccountLogin.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Password.setError("Empty Fields are not allowed");
            }

        }else if(email.isEmpty()){
            Email.setError("Empty Fields are not allowed");
        }else{
            Email.setError("Please Enter Correct Email");
        }


    }
}