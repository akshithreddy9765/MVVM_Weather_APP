package com.example.akshith.weatherapp.domain;

import java.util.List;

import io.reactivex.Observable;

public interface WeatherRepository {
    Observable<List<WeatherEntity>> getWeather(Double latitude, Double longitude);
}
