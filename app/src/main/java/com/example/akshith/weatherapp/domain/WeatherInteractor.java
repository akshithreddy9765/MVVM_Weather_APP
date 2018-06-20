package com.example.akshith.weatherapp.domain;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public interface WeatherInteractor {
    Observable<List<WeatherEntity>> getWeather(Double latitude, Double longitude);
}
