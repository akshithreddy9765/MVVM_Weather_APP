package com.example.akshith.weatherapp.domain;


public class WeatherEntity {
    private final String city;
    private final Double currentTemperature;
    private final Double maxTemperature;
    private final Double minTemperature;
    private final String dateTime;

    public WeatherEntity(String city, Double currentTemperature, Double maxTemperature, Double minTemperature, String dateTime) {
        this.city = city;
        this.currentTemperature = currentTemperature;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.dateTime = dateTime;
    }

    public String getCity() {
        return city;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public String getDateTime() {
        return dateTime;
    }
}
