package com.example.automobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash_screen extends Activity {

    TextView  app_name;
    ImageView logo;
    TextView distributor,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        logo = findViewById(R.id.logo);
        distributor = findViewById(R.id.distributor);
        name = findViewById(R.id.name);
        app_name = findViewById(R.id.app_name);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startEnterAnimation();
            }
        }, 0000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        }, 2000);
    }

    private void startEnterAnimation() {
        app_name.startAnimation(AnimationUtils.loadAnimation(com.example.automobile.splash_screen.this, R.anim.bottom));
        logo.startAnimation(AnimationUtils.loadAnimation(com.example.automobile.splash_screen.this, R.anim.p_in));


        logo.setVisibility(View.VISIBLE);
        app_name.setVisibility(View.VISIBLE);
        distributor.setVisibility(View.VISIBLE);
        name.setVisibility(View.VISIBLE);
    }
}

