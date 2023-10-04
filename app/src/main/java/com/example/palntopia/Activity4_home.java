package com.example.palntopia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

    class activity4_home extends AppCompatActivity {

    private EditText etPlantName, etPlantWater, etPlantSun, etPlantDescription;
    private Button btnUploadPhoto, btnSavePlant;

    // Firebase
    private DatabaseReference mDatabase;

    // Constant for image selection
    private static final int PICK_IMAGE = 1;

    // Uri for selected image
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity4_home);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://store-data-realtime-79013-default-rtdb.firebaseio.com/");

        // Initialize Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference().child("plants");

        // Initialize Views
        etPlantName = findViewById(R.id.etPlantName);
        etPlantWater = findViewById(R.id.etPlantWater);
        etPlantSun = findViewById(R.id.etPlantSun);
        etPlantDescription = findViewById(R.id.etPlantDescription);
        btnUploadPhoto = findViewById(R.id.btnUploadPhoto);
        btnSavePlant = findViewById(R.id.btnSavePlant);

        // Set onClickListener for Upload Photo Button
        btnUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        // Set onClickListener for Save Button
        btnSavePlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePlantData();
            }
        });

        // Add other necessary functionality here...
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            // Handle the selected image as needed (e.g., display in an ImageView)
        }
    }

    private void savePlantData() {
        // Get values from EditText fields
        String plantName = etPlantName.getText().toString().trim();
        String plantWater = etPlantWater.getText().toString().trim();
        String plantSun = etPlantSun.getText().toString().trim();
        String plantDescription = etPlantDescription.getText().toString().trim();

        // Check if any field is empty
        if (plantName.isEmpty() || plantWater.isEmpty() || plantSun.isEmpty() || plantDescription.isEmpty()) {
            // Handle empty fields
            // You can show a toast message or any other UI indication
        } else {
            // Create a unique ID for the plant data
            String plantId = mDatabase.push().getKey();

            // Create Plant object
            Plant plant = new Plant(plantId, plantName, plantWater, plantSun, plantDescription);

            // Save data to Firebase
            mDatabase.child(plantId).setValue(plant);

            // Clear EditText fields
            etPlantName.setText("");
            etPlantWater.setText("");
            etPlantSun.setText("");
            etPlantDescription.setText("");

            // Inform the user that the data has been saved
            // You can use a Toast or any other UI indication
        }
    }

    // Add other necessary methods here...

    // Plant Model Class
    public class Plant {
        private String id;
        private String name;
        private String water;
        private String sun;
        private String description;

        public Plant() {
            // Default constructor required for calls to DataSnapshot.getValue(Plant.class)
        }

        public Plant(String id, String name, String water, String sun, String description) {
            this.id = id;
            this.name = name;
            this.water = water;
            this.sun = sun;
            this.description = description;
        }

        // Add getters and setters as needed...

    }
}
