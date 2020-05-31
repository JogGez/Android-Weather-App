package com.example.androidweatherapp.interfaces;

import com.example.androidweatherapp.api.models.currentweatherdata.CurrentWeatherData;
import com.example.androidweatherapp.api.models.currentweatherdatalist.List;

public interface ItemClicked {
    void onItemClicked(String string);
    void onItemClicked( List data);
}
