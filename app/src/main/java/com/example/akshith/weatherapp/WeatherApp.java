package com.example.akshith.weatherapp;

import android.app.Application;


public class WeatherApp extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if (component == null) {
            buildAppComponent();
        }
    }

    public static AppComponent getComponent() {
        return component;
    }

    private void buildAppComponent() {
        component = DaggerAppComponent.builder().build();
    }
}
