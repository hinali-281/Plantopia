package com.example.palntopia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home_page extends AppCompatActivity {
    TextView user;
    ImageView account_details_page,post_add_page,AloVera,PeaceLily,Dandelion,RoseMary,SnackPlant;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TextView textViewUsername = findViewById(R.id.textView12);
        account_details_page= findViewById(R.id.account_details_page);
        post_add_page= findViewById(R.id.post_add_page);
        AloVera=findViewById(R.id.AloVera);
        PeaceLily=findViewById(R.id.PeaceLily);
        Dandelion=findViewById(R.id.Dandelion);
        RoseMary=findViewById(R.id.RoseMary);
        SnackPlant=findViewById(R.id.SnackPlant);

        AloVera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, AloVera_IMG.class);
                startActivity(intent);
            }
        });
        PeaceLily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, PeaceLiy_img.class);
                startActivity(intent);
            }
        });
        Dandelion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Dandelion_img.class);
                startActivity(intent);
            }
        });
        RoseMary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, RoseMary_img.class);
                startActivity(intent);
            }
        });
        SnackPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, RoseMary_img.class);
                startActivity(intent);
            }
        });
        account_details_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, account_activity.class);
                intent.putExtra("USERNAME", getIntent().getStringExtra("USERNAME"));
                startActivity(intent);
            }
        });
        post_add_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page.this, Activity4_home.class);
                startActivity(intent);
            }
        });

        // Retrieve the username from the Intent
        String username = getIntent().getStringExtra("USERNAME");

        // Set the username to the TextView
        textViewUsername.setText(username);

    }
}