package com.example.akshith.weatherapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {
    @SerializedName("cod")
    private String code;
    private City city;
    @SerializedName("list")
    private List<WeatherList> weatherList;

    public String getCode() {
        return code;
    }

    public City getCity() {
        return city;
    }

    public List<WeatherList> getWeatherList() {
        return weatherList;
    }
}
