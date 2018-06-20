package com.example.akshith.weatherapp.data;

import com.example.akshith.weatherapp.BuildConfig;
import com.example.akshith.weatherapp.domain.WeatherEntity;
import com.example.akshith.weatherapp.domain.WeatherRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherService weatherService;
    private final WeatherEntityMapper weatherEntityMapper;

    @Inject
    public WeatherRepositoryImpl(WeatherService weatherService, WeatherEntityMapper weatherEntityMapper) {
        this.weatherService = weatherService;
        this.weatherEntityMapper = weatherEntityMapper;
    }

    @Override
    public Observable<List<WeatherEntity>> getWeather(Double latitude, Double longitude) {
        return weatherService.getWeather(latitude, longitude, BuildConfig.weather_api_key)
                .map(weatherEntityMapper::getWeatherEntity);
    }
}
