package com.example.akshith.weatherapp.presentation;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;

import com.example.akshith.weatherapp.domain.WeatherInteractor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class MainViewModel extends BaseObservable {
    public ObservableBoolean showProgress = new ObservableBoolean(false);
    private final WeatherInteractor weatherInteractor;
    private final MainViewModelMapper mainViewModelMapper;
    private CompositeDisposable disposable = new CompositeDisposable();
    public BehaviorSubject<String> errorSubject = BehaviorSubject.create();
    public BehaviorSubject<List<WeatherViewModel>> dataSubject = BehaviorSubject.create();
    private final RxUtil rxUtil;

    @Inject
    public MainViewModel(WeatherInteractor weatherInteractor, MainViewModelMapper mainViewModelMapper, RxUtil rxUtil) {
        this.weatherInteractor = weatherInteractor;
        this.mainViewModelMapper = mainViewModelMapper;
        this.rxUtil = rxUtil;
    }

    public void getWeather(Double latitude, Double longitude) {
        disposable.add(weatherInteractor.getWeather(latitude, longitude)
                .subscribeOn(rxUtil.subscribeOnScheduler)
                .observeOn(rxUtil.observeOnScheduler)
                .doOnSubscribe(__ -> showProgress.set(true))
                .doOnTerminate(() -> showProgress.set(false))
                .subscribe(weatherEntityList -> {
                    if (weatherEntityList.size() > 0) {
                        List<WeatherViewModel> viewModels = mainViewModelMapper.getWeatherViewModelsFromWeatherEntity(weatherEntityList);
                        dataSubject.onNext(viewModels);
                    }
                }, throwable -> errorSubject.onNext("Error Occurred While fetching data")));
    }

    public void onDestroy() {
        if (!disposable.isDisposed()) {
            disposable.clear();
        }
    }
}
