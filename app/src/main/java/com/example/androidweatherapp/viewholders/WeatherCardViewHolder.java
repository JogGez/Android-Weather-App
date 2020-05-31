package com.example.androidweatherapp.viewholders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidweatherapp.api.models.currentweatherdata.CurrentWeatherData;
import com.example.androidweatherapp.api.models.currentweatherdatalist.CurrentWeatherDataList;
import com.example.androidweatherapp.api.models.currentweatherdatalist.List;
import com.example.androidweatherapp.interfaces.ItemClicked;

public class WeatherCardViewHolder extends RecyclerView.ViewHolder {

    public List currentData;
    private int cityId;
    private TextView city;
    private TextView weatherDescription;
    private TextView currentTemp;
    private TextView maxTemp;
    private TextView minTemp;
    private ImageView weatherImage;
    private ItemClicked activity;


    public WeatherCardViewHolder(@NonNull final View itemView, TextView city, TextView weatherDescription, TextView currentTemp, TextView maxTemp, TextView minTemp, ImageView weatherImage, Context context) {
        super(itemView);
        this.activity = (ItemClicked) context;
        this.city = city;
        this.weatherDescription = weatherDescription;
        this.currentTemp = currentTemp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.weatherImage = weatherImage;

        itemView.setOnClickListener(v -> activity.onItemClicked(Integer.toString(cityId), currentData));
    }

    public void setCity(String city) {
        this.city.setText(city);
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription.setText(weatherDescription);
    }

    public void setCurrentWeatherData(List data){
        this.currentData = data;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp.setText(currentTemp);
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp.setText(maxTemp);
    }

    public void setMinTemp(String minTemp) {
        this.minTemp.setText(minTemp);
    }

    public void setWeatherImage(Drawable weatherImage) {
        this.weatherImage.setImageDrawable(weatherImage);
    }

    public void setCityId(Integer id) {
        cityId = id;
    }
}