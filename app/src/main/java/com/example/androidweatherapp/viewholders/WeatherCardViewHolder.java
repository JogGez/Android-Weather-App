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

import java.text.DecimalFormat;

public class WeatherCardViewHolder extends RecyclerView.ViewHolder {

    public List currentData;
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

        itemView.setOnClickListener(v -> activity.onItemClicked(currentData));
    }

    public void setCurrentWeatherData(List data){
        this.currentData = data;
        this.city.setText(data.getName() + ", " + data.getSys().getCountry());
        this.weatherDescription.setText(data.getWeather().get(0).getDescription());
        this.currentTemp.setText(new DecimalFormat("#").format(data.getMain().getTemp()) + "°");
        this.maxTemp.setText(new DecimalFormat("#").format(data.getMain().getTempMax()) + "°");
        this.minTemp.setText(new DecimalFormat("#").format(data.getMain().getTempMin()) + "°");
    }

    public void setWeatherImage(Drawable weatherImage) {
        this.weatherImage.setImageDrawable(weatherImage);
    }

}