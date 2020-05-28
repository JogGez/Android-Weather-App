package com.example.androidweatherapp;

import android.content.Context;
import com.example.androidweatherapp.api.models.currentweatherdata.CurrentWeatherData;
import com.example.androidweatherapp.storage.Weather;
import com.example.androidweatherapp.storage.WeatherDatabase;

import java.util.List;

public class Data {

    private static Data INSTANCE;
    private Context context;
    private WeatherDatabase weatherDatabase;
    private List<Weather> weatherList;
    private List<CurrentWeatherData> currentWeatherDataList;
    private boolean dataChange;

    private Data(Context context){
        this.context = context;
        weatherDatabase = WeatherDatabase.getAppDatabase(this.context);
        getWeather();
    }

    public static Data getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = new Data(context);
        }

        return INSTANCE;
    }

    public List<CurrentWeatherData> getCurrentWeatherDataList() {
        return currentWeatherDataList;
    }

    public void setCurrentWeatherDataList(List<CurrentWeatherData> currentWeatherDataList) {
        this.currentWeatherDataList = currentWeatherDataList;
    }


    public List<Weather> getWeather(){
        updateWeatherList();
        return weatherList;
    }

    private void updateWeatherList(){
        weatherList = weatherDatabase.weatherDao().getAll();
    }

    public void addWeather(String cityId, String city, String countryCode){
        Weather weather = new Weather();
        weather.cityId = cityId;
        weather.city = city;
        weather.countryCode = countryCode;
        weatherDatabase.weatherDao().insert(weather);
        updateWeatherList();
    }



}
