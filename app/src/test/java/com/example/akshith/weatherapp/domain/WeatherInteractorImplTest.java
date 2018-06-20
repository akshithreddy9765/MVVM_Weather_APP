package com.example.akshith.weatherapp.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherInteractorImplTest {

    private WeatherRepository mockWeatherRepository = mock(WeatherRepository.class);
    private WeatherInteractorImpl subject;

    @Before
    public void setUp() {
        subject = new WeatherInteractorImpl(mockWeatherRepository);
    }

    @Test
    public void test_when_calling_getWeather_with_latitude_and_longitude_then_it_should_call_WeatherRepository_getWeather() {
        double lat = 30.123;
        double lon = -91.123;
        when(mockWeatherRepository.getWeather(lat, lon)).thenReturn(Observable.just(getEntity()));
        subject.getWeather(lat, lon).test()
                .assertComplete()
                .assertNoErrors()
                .assertValueCount(1)
                .assertValue(weatherEntities -> weatherEntities.size() == 1);
        verify(mockWeatherRepository).getWeather(lat, lon);
    }

    private List<WeatherEntity> getEntity() {
        List<WeatherEntity> weatherEntities = new ArrayList<>();
        weatherEntities.add(new WeatherEntity("city1", 220.12, 230.4, 212.4, "date time1"));
        return weatherEntities;
    }
}