package com.example.akshith.weatherapp.presentation;

import android.databinding.BindingAdapter;
import android.view.View;

public class CustomBinder {
    @BindingAdapter({"bind:visible"})
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
