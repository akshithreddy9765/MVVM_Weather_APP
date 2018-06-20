package com.example.akshith.weatherapp.data;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("/data/2.5/forecast?")
    Observable<WeatherResponse> getWeather(@Query("lat") Double latitude, @Query("lon") Double longitude, @Query("appid") String appId);
}
