package com.example.akshith.weatherapp.presentation;

public class WeatherViewModel {
    public final String city;
    public final String currentTemperature;
    public final String maxTemperature;
    public final String minTemperature;
    public final String dateTime;

    public WeatherViewModel(String city, String currentTemperature, String maxTemperature, String minTemperature, String dateTime) {
        this.city = city;
        this.currentTemperature = currentTemperature;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.dateTime = dateTime;
    }
}
