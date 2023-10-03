package com.example.palntopia;

public class DataClass {

    private String dataName;
    private String dataType;
    private String dataWater;
    private String dataSun;
    private String dataImage;

    public String getDataName() {
        return dataName;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDataWater() {
        return dataWater;
    }

    public String getDataSun() {
        return dataSun;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(String dataName, String dataType, String dataWater, String dataSun, String dataImage) {
        this.dataName = dataName;
        this.dataType = dataType;
        this.dataWater = dataWater;
        this.dataSun = dataSun;
        this.dataImage = dataImage;
    }
}
