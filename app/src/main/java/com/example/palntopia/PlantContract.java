package com.example.palntopia;

import android.provider.BaseColumns;

public final class PlantContract {
    private PlantContract() {}

    public static class PlantEntry implements BaseColumns {
        public static final String TABLE_NAME = "plants";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_WATER = "water";
        public static final String COLUMN_SUN = "sun";
    }
}

