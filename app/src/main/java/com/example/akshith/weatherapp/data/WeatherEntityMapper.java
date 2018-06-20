package com.example.akshith.weatherapp.data;

import com.example.akshith.weatherapp.domain.WeatherEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WeatherEntityMapper {
    @Inject
    public WeatherEntityMapper() {
    }

    public List<WeatherEntity> getWeatherEntity(WeatherResponse weatherResponse) {
        List<WeatherEntity> weatherEntities = new ArrayList<>();
        String cityName = weatherResponse.getCity().getCityName();
        for (WeatherList weatherList : weatherResponse.getWeatherList()) {
            WeatherMain weatherMain = weatherList.getWeatherMain();
            weatherEntities.add(new WeatherEntity(cityName,
                    weatherMain.getCurrentTemperature(),
                    weatherMain.getMaxTemperature(),
                    weatherMain.getMinTemperature(),
                    weatherList.getDateTime()));
        }
        return weatherEntities;
    }
}
