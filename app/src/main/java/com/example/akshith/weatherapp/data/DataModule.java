package com.example.akshith.weatherapp.data;

import com.example.akshith.weatherapp.domain.WeatherRepository;
import com.google.gson.Gson;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = DataModule.RepositoryModule.class)
public class DataModule {

    @Provides
    public HttpLoggingInterceptor.Level provideLoggingInterceptor() {
        return HttpLoggingInterceptor.Level.BODY;
    }

    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    public WeatherService provideWeatherService(ServiceFactory serviceFactory) {
        return serviceFactory.createService(WeatherService.class);
    }

    @Module
    public abstract class RepositoryModule {
        @Binds
        public abstract WeatherRepository provideWeatherRepository(WeatherRepositoryImpl weatherRepositoryImpl);
    }
}
