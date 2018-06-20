package com.example.akshith.weatherapp.presentation;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxUtil {
    public  Scheduler subscribeOnScheduler;
    public  Scheduler observeOnScheduler;

    @Inject
    public RxUtil() {
        subscribeOnScheduler = Schedulers.newThread();
        observeOnScheduler= AndroidSchedulers.mainThread();
    }
}
