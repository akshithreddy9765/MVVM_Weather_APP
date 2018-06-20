package com.example.akshith.weatherapp.data;

import com.example.akshith.weatherapp.domain.WeatherEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherRepositoryImplTest {
    private WeatherService mockService = mock(WeatherService.class);
    private WeatherEntityMapper mockEntityMapper = mock(WeatherEntityMapper.class);

    private WeatherRepositoryImpl subject;

    @Before
    public void setUp() {
        subject = new WeatherRepositoryImpl(mockService, mockEntityMapper);
    }

    @Test
    public void test_when_calling_getWeather_with_latitude_and_longitude_it_should_call_weatherService_getWeather() {
        Double lat = 39.098;
        Double lon = -92.982;
        when(mockService.getWeather(eq(lat), eq(lon), anyString())).thenReturn(Observable.just(mock(WeatherResponse.class)));
        subject.getWeather(lat, lon);
        verify(mockService).getWeather(eq(lat), eq(lon), anyString());
    }

    @Test
    public void test_when_calling_getWeather_with_latitude_and_longitude_and_weatherService_getWeather_returns_success_then_it_should_call_WeatherEntityMapper_getWeatherEntity() {
        Double lat = 39.098;
        Double lon = -92.982;
        WeatherResponse mockResponse = mock(WeatherResponse.class);
        when(mockService.getWeather(eq(lat), eq(lon), anyString())).thenReturn(Observable.just(mockResponse));
        when(mockEntityMapper.getWeatherEntity(mockResponse)).thenReturn(getEntity());
        subject.getWeather(lat, lon).test()
                .assertComplete()
                .assertNoErrors()
                .assertValueCount(1)
                .assertValue(weatherEntities -> weatherEntities.size() == 2);
        verify(mockService).getWeather(eq(lat), eq(lon), anyString());
        verify(mockEntityMapper).getWeatherEntity(mockResponse);
    }

    @Test
    public void test_when_calling_getWeather_with_lat_lan_and_weatherService_getWeather_throws_exception_then_it_should_not_call_WeatherEntityMapper_getWeatherEntity() {
        Double lat = 39.098;
        Double lon = -92.982;
        when(mockService.getWeather(eq(lat), eq(lon), anyString())).thenReturn(Observable.error(new RuntimeException()));
        subject.getWeather(lat, lon).test()
                .assertNotComplete()
                .assertError(RuntimeException.class)
                .assertNoValues();
        verify(mockService).getWeather(eq(lat), eq(lon), anyString());
        verify(mockEntityMapper, never()).getWeatherEntity(any());
    }

    private List<WeatherEntity> getEntity() {
        List<WeatherEntity> weatherEntities = new ArrayList<>();
        weatherEntities.add(new WeatherEntity("city1", 220.12, 230.4, 212.4, "date time1"));
        weatherEntities.add(new WeatherEntity("city2", 221.12, 231.4, 213.4, "date time2"));
        return weatherEntities;
    }
}