package com.example.palntopia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3_login extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    Button loginButton;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://plantopia-login-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_login);

        // Initialize UI elements
        usernameEditText = findViewById(R.id.login_usernm);
        passwordEditText = findViewById(R.id.login_password);

        loginButton = findViewById(R.id.button_login);

        // Set click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username =  usernameEditText.getText().toString().trim();
                final String password =  passwordEditText.getText().toString().trim();
                // Call your validation method
                if ( username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity3_login.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();


                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(username)) {
                                final String getPassword = snapshot.child(username).child("password").getValue(String.class);

                                if (getPassword.equals(password)) {
                                    Toast.makeText(MainActivity3_login.this, "successfully Logged in", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity3_login.this, activity4_home.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(MainActivity3_login.this, "check your Username and Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error){

                            }

                    });
                }
            }
        });
    }

    // Method to validate user inputs
    private boolean validateInputs() {
        String username = usernameEditText.getText().toString().trim();

        String password = passwordEditText.getText().toString().trim();

        // Perform your validation logic here
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return false;
        }



        if (password.isEmpty() || password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Return true if all validations pass
        return true;
    }
}