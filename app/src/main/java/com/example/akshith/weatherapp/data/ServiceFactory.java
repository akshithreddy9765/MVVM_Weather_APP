package com.example.akshith.weatherapp.data;

import com.google.gson.Gson;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {
    private static String BASE_URL = "https:api.openweathermap.org/";
    private final Gson gson;
    private final HttpLoggingInterceptor.Level level;

    @Inject
    public ServiceFactory(Gson gson, HttpLoggingInterceptor.Level level) {
        this.gson = gson;
        this.level = level;
    }

    public <T> T createService(Class<T> serviceType) {
        return getAdapter().create(serviceType);
    }

    private Retrofit getAdapter() {
        return new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(level)).build();
    }
}
