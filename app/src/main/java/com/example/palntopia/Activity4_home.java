package com.example.palntopia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity4_home extends AppCompatActivity {
    private EditText plantNameEditText, plantWaterEditText, plantSunEditText;
    private TextView resultTextView;
    private Button addButton, updateButton, viewButton, deleteButton;

    private PlantDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity4_home);

        dbHelper = new PlantDbHelper(this);

        plantNameEditText = findViewById(R.id.plantNameEditText);
        plantWaterEditText = findViewById(R.id.plantWaterEditText);
        plantSunEditText = findViewById(R.id.plantSunEditText);
        resultTextView = findViewById(R.id.resultTextView);

        addButton = findViewById(R.id.addButton);
        updateButton = findViewById(R.id.updateButton);
        viewButton = findViewById(R.id.viewButton);
        deleteButton = findViewById(R.id.deleteButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPlant();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlant();
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPlants();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePlant();
            }
        });
    }

    private void addPlant() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PlantContract.PlantEntry.COLUMN_NAME, plantNameEditText.getText().toString());
        values.put(PlantContract.PlantEntry.COLUMN_WATER, plantWaterEditText.getText().toString());
        values.put(PlantContract.PlantEntry.COLUMN_SUN, plantSunEditText.getText().toString());

        long newRowId = db.insert(PlantContract.PlantEntry.TABLE_NAME, null, values);

        resultTextView.setText("Plant added with ID: " + newRowId);
    }

    private void updatePlant() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PlantContract.PlantEntry.COLUMN_WATER, plantWaterEditText.getText().toString());
        values.put(PlantContract.PlantEntry.COLUMN_SUN, plantSunEditText.getText().toString());

        String selection = PlantContract.PlantEntry.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = { plantNameEditText.getText().toString() };

        int count = db.update(
                PlantContract.PlantEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        resultTextView.setText("Updated " + count + " rows");
    }

    private void viewPlants() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                PlantContract.PlantEntry._ID,
                PlantContract.PlantEntry.COLUMN_NAME,
                PlantContract.PlantEntry.COLUMN_WATER,
                PlantContract.PlantEntry.COLUMN_SUN
        };

        Cursor cursor = db.query(
                PlantContract.PlantEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        StringBuilder result = new StringBuilder("Plant details:\n");
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(((Cursor) cursor).getColumnIndexOrThrow(PlantContract.PlantEntry._ID));
            String plantName = cursor.getString(cursor.getColumnIndexOrThrow(PlantContract.PlantEntry.COLUMN_NAME));
            String water = cursor.getString(cursor.getColumnIndexOrThrow(PlantContract.PlantEntry.COLUMN_WATER));
            String sun = cursor.getString(cursor.getColumnIndexOrThrow(PlantContract.PlantEntry.COLUMN_SUN));

            result.append(itemId).append(": ").append(plantName).append(" - Water: ").append(water)
                    .append(", Sun: ").append(sun).append("\n");
        }
        cursor.close();

        resultTextView.setText(result.toString());
    }

    private void deletePlant() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = PlantContract.PlantEntry.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = { plantNameEditText.getText().toString() };

        int deletedRows = db.delete(PlantContract.PlantEntry.TABLE_NAME, selection, selectionArgs);

        resultTextView.setText("Deleted " + deletedRows + " rows");
    }
}
