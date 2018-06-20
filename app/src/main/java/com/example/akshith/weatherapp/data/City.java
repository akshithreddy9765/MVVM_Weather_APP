package com.example.akshith.weatherapp.data;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("name")
    private String cityName;

    public String getCityName() {
        return cityName;
    }
}
