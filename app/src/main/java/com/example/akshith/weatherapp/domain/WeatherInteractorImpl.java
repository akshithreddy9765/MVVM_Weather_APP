package com.example.akshith.weatherapp.domain;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class WeatherInteractorImpl implements WeatherInteractor {
    private final WeatherRepository weatherRepository;

    @Inject
    public WeatherInteractorImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Observable<List<WeatherEntity>> getWeather(Double latitude, Double longitude) {
        return weatherRepository.getWeather(latitude, longitude);
    }
}
