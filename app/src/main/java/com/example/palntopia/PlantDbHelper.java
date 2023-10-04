package com.example.palntopia;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlantDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Plants.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PlantContract.PlantEntry.TABLE_NAME + " (" +
                    PlantContract.PlantEntry._ID + " INTEGER PRIMARY KEY," +
                    PlantContract.PlantEntry.COLUMN_NAME + " TEXT," +
                    PlantContract.PlantEntry.COLUMN_WATER + " TEXT," +
                    PlantContract.PlantEntry.COLUMN_SUN + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PlantContract.PlantEntry.TABLE_NAME;

    public PlantDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

