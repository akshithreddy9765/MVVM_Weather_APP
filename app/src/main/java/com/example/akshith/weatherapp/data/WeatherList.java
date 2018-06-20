package com.example.akshith.weatherapp.data;

import com.google.gson.annotations.SerializedName;

public class WeatherList {
    @SerializedName("main")
    private WeatherMain weatherMain;
    @SerializedName("dt_txt")
    private String dateTime;

    public WeatherMain getWeatherMain() {
        return weatherMain;
    }

    public String getDateTime() {
        return dateTime;
    }
}
