package com.example.palntopia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class account_activity extends AppCompatActivity {
    TextView account_username, account_phone, account_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        account_username = findViewById(R.id.account_username);
        account_phone = findViewById(R.id.account_Phone);
        account_email = findViewById(R.id.textView3);

        // Retrieve user details from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("USERNAME");
            String email = intent.getStringExtra("EMAIL");
            String phone = intent.getStringExtra("PHONE");

            // Set user details to the TextViews
            account_username.setText(username);
            account_phone.setText(phone);
            account_email.setText(email);
        }
    }
}