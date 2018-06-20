package com.example.akshith.weatherapp.data;

import com.google.gson.annotations.SerializedName;

public class WeatherMain {
    @SerializedName("temp")
    private Double currentTemperature;
    @SerializedName("temp_min")
    private Double minTemperature;
    @SerializedName("temp_max")
    private Double maxTemperature;

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }
}
