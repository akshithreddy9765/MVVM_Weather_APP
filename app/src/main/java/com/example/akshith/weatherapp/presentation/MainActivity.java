package com.example.akshith.weatherapp.presentation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.akshith.weatherapp.R;
import com.example.akshith.weatherapp.WeatherApp;
import com.example.akshith.weatherapp.databinding.ActivityMainBinding;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends AppCompatActivity {

    private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1002;
    @Inject
    MainViewModel mainViewModel;
    private ActivityMainBinding dataBinding;
    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeatherApp.getComponent().inject(this);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dataBinding.setViewModel(mainViewModel);
        subscribeForSubjects();
        initRecyclerView();
        getCurrentLocation();
    }

    private void subscribeForSubjects() {
        disposable.add(mainViewModel.errorSubject.subscribe(this::showToast));
        disposable.add(mainViewModel.dataSubject.subscribe(weatherViewModels -> weatherAdapter.setData(weatherViewModels)));
    }

    private void initRecyclerView() {
        recyclerView = dataBinding.recyclerView;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        weatherAdapter = new WeatherAdapter();
        recyclerView.setAdapter(weatherAdapter);
    }

    private void getCurrentLocation() {
        if (isLocationPermissionGranted()) {
            getCurrentLocationWeather();
        } else {
            requestLocationPermission();
        }
    }

    private void getCurrentLocationWeather() {
        Location location = getCurrentCoordinates();
        if (location != null) {
            mainViewModel.getWeather(location.getLatitude(), location.getLongitude());
        }
    }

    private boolean isLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocationWeather();
            } else {
                mainViewModel.errorSubject.onNext("Location Permission Denied");
            }
        }
    }

    @SuppressLint("MissingPermission")
    private Location getCurrentCoordinates() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            return locationManager.getLastKnownLocation(provider);
        }
        return null;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.onDestroy();
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
