package com.example.akshith.weatherapp;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

public class WeatherApp extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if (component == null) {
            buildAppComponent();
        }
        AndroidThreeTen.init(this);
    }

    public static AppComponent getComponent() {
        return component;
    }

    private void buildAppComponent() {
        component = DaggerAppComponent.builder().build();
    }
}
