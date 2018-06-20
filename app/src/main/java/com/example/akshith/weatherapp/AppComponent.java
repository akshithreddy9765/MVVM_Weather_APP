package com.example.akshith.weatherapp;

import com.example.akshith.weatherapp.data.DataModule;
import com.example.akshith.weatherapp.domain.DomainModule;
import com.example.akshith.weatherapp.presentation.AppModule;
import com.example.akshith.weatherapp.presentation.MainActivity;

import dagger.Component;

@Component(modules = {AppModule.class, DomainModule.class, DataModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
