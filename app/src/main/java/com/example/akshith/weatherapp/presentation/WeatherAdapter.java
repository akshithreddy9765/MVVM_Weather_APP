package com.example.akshith.weatherapp.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.akshith.weatherapp.databinding.WeatherRowBinding;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private LayoutInflater layoutInflater;
    private List<WeatherViewModel> data;

    public WeatherAdapter() {
        data = new ArrayList<>();
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        WeatherRowBinding binding = WeatherRowBinding.inflate(layoutInflater, parent, false);
        return new WeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        WeatherViewModel weatherViewModel = data.get(position);
        holder.setRowItemBinding(weatherViewModel);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<WeatherViewModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    static final class WeatherViewHolder extends RecyclerView.ViewHolder {
        final WeatherRowBinding rowItemBinding;

        WeatherViewHolder(WeatherRowBinding rowItemBinding) {
            super(rowItemBinding.getRoot());
            this.rowItemBinding = rowItemBinding;
        }

        void setRowItemBinding(WeatherViewModel weatherViewModel) {
            rowItemBinding.setWeatherViewModel(weatherViewModel);
        }
    }
}
