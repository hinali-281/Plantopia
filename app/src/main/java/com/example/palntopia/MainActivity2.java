package com.example.palntopia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity  {
    TextView account_login;
    private EditText etUsername, etEmailAddress, etPassword, etConfirmPassword,phoneEditText;
    private Button btnSignUp;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://plantopia-login-default-rtdb.firebaseio.com/");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        phoneEditText=findViewById(R.id.txt_phone);
        etUsername = findViewById(R.id.editTextText);
        etUsername = findViewById(R.id.editTextText);
        etEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        etPassword = findViewById(R.id.editTextTextPassword);
        etConfirmPassword = findViewById(R.id.editTextTextPassword2);
        btnSignUp = findViewById(R.id.button);
        account_login=findViewById(R.id.account_login);

        account_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3_login.class);
                startActivity(intent);

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final String username = etUsername.getText().toString().trim();
                final String email = etEmailAddress.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                final String confirmPassword = etConfirmPassword.getText().toString().trim();
                final String phoneNumber = phoneEditText.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(MainActivity2.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(MainActivity2.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(MainActivity2.this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(MainActivity2.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }else if (phoneNumber.isEmpty() || !isValidPhoneNumber(phoneNumber)) {
                    Toast.makeText(MainActivity2.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                }
                else {
                  databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          if(snapshot.hasChild(username)){
                              Toast.makeText(MainActivity2.this, "Phone no. is already registered", Toast.LENGTH_SHORT).show();
                          }
                          else {
                              databaseReference.child("users").child(username).child("username").setValue(etUsername.getText().toString());
                              databaseReference.child("users").child(username).child("email").setValue(etEmailAddress.getText().toString());
                              databaseReference.child("users").child(username).child("password").setValue(etPassword.getText().toString());
                              Toast.makeText(MainActivity2.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent(MainActivity2.this, MainActivity3_login.class);
                              startActivity(intent);
                              finish();

                          }
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });


                    // Registration is successful (replace with your registration logic)
                    // For now, just show a toast message to indicate success
                    Toast.makeText(MainActivity2.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity2.this, MainActivity3_login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean isValidEmail(CharSequence email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();


    }
    private boolean isValidPhoneNumber(String phoneNumber) {
        // For simplicity, let's assume a valid phone number is 10 digits
        return phoneNumber.length() == 10 && TextUtils.isDigitsOnly(phoneNumber);
    }

}
