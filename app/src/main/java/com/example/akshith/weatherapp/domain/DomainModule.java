package com.example.akshith.weatherapp.domain;

import com.example.akshith.weatherapp.data.WeatherRepositoryImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DomainModule {
    @Binds
    public abstract WeatherInteractor provideWeatherInteractor(WeatherInteractorImpl weatherInteractorImpl);

}