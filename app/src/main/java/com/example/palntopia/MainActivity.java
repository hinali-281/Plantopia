package com.example.palntopia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {




        private static final int SPLASH_SCREEN_DURATION = 4000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent mainIntent = new Intent(MainActivity.this, MainActivity2.class); // Replace with your main activity
                    startActivity(mainIntent);

                    finish();
                }
            }, SPLASH_SCREEN_DURATION);
        }
    }























