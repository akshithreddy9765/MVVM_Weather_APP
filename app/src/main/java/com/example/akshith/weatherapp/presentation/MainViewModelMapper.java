package com.example.akshith.weatherapp.presentation;

import com.example.akshith.weatherapp.domain.WeatherEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainViewModelMapper {

    @Inject
    public MainViewModelMapper() {
    }

    public List<WeatherViewModel> getWeatherViewModelsFromWeatherEntity(List<WeatherEntity> weatherEntities) {
        List<WeatherViewModel> weatherViewModels = new ArrayList<>();
        for (WeatherEntity weatherEntity : weatherEntities) {
            weatherViewModels.add(new WeatherViewModel("City: " + weatherEntity.getCity(),
                    "now: " + weatherEntity.getCurrentTemperature().toString(),
                    "max: " + weatherEntity.getMaxTemperature().toString(),
                    "min: " + weatherEntity.getMinTemperature().toString(),
                    "date&time: " + weatherEntity.getDateTime()));
        }
        return weatherViewModels;
    }
}
