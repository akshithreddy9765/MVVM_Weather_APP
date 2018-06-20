package com.example.akshith.weatherapp.presentation;

import com.example.akshith.weatherapp.domain.WeatherEntity;
import com.example.akshith.weatherapp.domain.WeatherInteractor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainViewModelTest {

    private WeatherInteractor mockInteractor = mock(WeatherInteractor.class);
    private MainViewModelMapper mockMapper = mock((MainViewModelMapper.class));
    private RxUtil rxUtil = mock(RxUtil.class);
    private MainViewModel subject;

    @Before
    public void setUp() {
        subject = new MainViewModel(mockInteractor, mockMapper, rxUtil);
        rxUtil.observeOnScheduler = Schedulers.trampoline();
        rxUtil.subscribeOnScheduler = Schedulers.trampoline();
    }

    @Test
    public void test_when_calling_get_weather_with_latitude_and_longitude_when_WeatherInteractor_getWeather_returns_empty_list_then_it_should_not_call_MainViewModelMapper_getWeatherViewModelsFromWeatherEntity() {
        Double lat = 32.012;
        Double lon = -92.12;
        List<WeatherEntity> weatherEntityList = new ArrayList<>();
        when(mockInteractor.getWeather(lat, lon)).thenReturn(Observable.just(weatherEntityList));
        subject.getWeather(lat, lon);
        verify(mockInteractor).getWeather(lat, lon);
        verify(mockMapper, never()).getWeatherViewModelsFromWeatherEntity(anyList());
        Assert.assertFalse(subject.dataSubject.hasValue());
        Assert.assertNull(subject.dataSubject.getValue());
        Assert.assertFalse(subject.showProgress.get());
        Assert.assertFalse(subject.errorSubject.hasValue());
    }

    @Test
    public void test_when_calling_get_weather_with_latitude_and_longitude_when_WeatherInteractor_getWeather_returns_non_empty_list_then_it_should_call_MainViewModelMapper_getWeatherViewModelsFromWeatherEntity() {
        Double lat = 32.012;
        Double lon = -92.12;
        when(mockInteractor.getWeather(lat, lon)).thenReturn(Observable.just(getEntities()));
        when(mockMapper.getWeatherViewModelsFromWeatherEntity(anyList())).thenReturn(getViewModels());
        subject.getWeather(lat, lon);
        verify(mockInteractor).getWeather(lat, lon);
        verify(mockMapper).getWeatherViewModelsFromWeatherEntity(anyList());
        Assert.assertTrue(subject.dataSubject.hasValue());
        List<WeatherViewModel> value = subject.dataSubject.getValue();
        Assert.assertNotNull(value);
        Assert.assertTrue(value.size() == 2);
        Assert.assertFalse(subject.showProgress.get());
        Assert.assertFalse(subject.errorSubject.hasValue());
    }

    @Test
    public void test_when_calling_get_weather_with_latitude_and_longitude_when_WeatherInteractor_getWeather_returns_throws_error_then_it_should_show_toast() {
        Double lat = 32.012;
        Double lon = -92.12;
        when(mockInteractor.getWeather(lat, lon)).thenReturn(Observable.error(new RuntimeException()));
        subject.getWeather(lat, lon);
        verify(mockInteractor).getWeather(lat, lon);
        verify(mockMapper, never()).getWeatherViewModelsFromWeatherEntity(anyList());
        Assert.assertFalse(subject.dataSubject.hasValue());
        Assert.assertFalse(subject.showProgress.get());
        Assert.assertTrue(subject.errorSubject.hasValue());
        String value = subject.errorSubject.getValue();
        Assert.assertEquals("Error Occurred While fetching data", value);
    }

    private List<WeatherViewModel> getViewModels() {
        List<WeatherViewModel> weatherViewModels = new ArrayList<>();
        weatherViewModels.add(new WeatherViewModel("city1", "224.1", "226.3", "221.2", "date_time1"));
        weatherViewModels.add(new WeatherViewModel("city2", "225.1", "229.3", "222.2", "date_time2"));
        return weatherViewModels;
    }

    private List<WeatherEntity> getEntities() {
        List<WeatherEntity> weatherEntities = new ArrayList<>();
        weatherEntities.add(new WeatherEntity("city1", 224.1, 226.3, 221.2, "date_time1"));
        weatherEntities.add(new WeatherEntity("city2", 225.1, 229.3, 222.2, "date_time2"));
        return weatherEntities;
    }
}