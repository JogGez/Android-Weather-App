package com.example.androidweatherapp.api;

import com.example.androidweatherapp.api.models.currentweatherdata.CurrentWeatherData;
import com.example.androidweatherapp.api.models.currentweatherdatalist.CurrentWeatherDataList;
import com.example.androidweatherapp.api.models.findcity.FindCity;
import com.example.androidweatherapp.api.models.forecastweatherdata.WeatherForecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterface {

    // Comma seperated id string
    @GET("data/2.5/group?units=metric")
    Call<CurrentWeatherDataList> getCurrentWeatherByList(@Query("id") String id, @Query("appid") String apiKey);

    @GET("data/2.5/weather?units=metric")
    Call<CurrentWeatherData> getCurrentWeatherByLatLon(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String apiKey);

    @GET("/data/2.5/weather?units=metric")
    Call<CurrentWeatherData> getCurrentWeatherById(@Query("id") String id, @Query("appid") String apiKey);

    @GET("/data/2.5/weather?units=metric")
    Call<CurrentWeatherData> getCurrentWeatherByCity(@Query("q") String city, @Query("appid") String apiKey);

    @GET("/data/2.5/find?units=metric")
    Call<FindCity> searchForCity(@Query("q") String city, @Query("appid") String apiKey);

    @GET("/data/2.5/forecast?units=metric")
    Call<WeatherForecast> getWeatherForecastById(@Query("id") String id, @Query("appid") String apiKey);

    @GET("/data/2.5/forecast?units=metric")
    Call<WeatherForecast> getWeatherForecastByCity(@Query("q") String city, @Query("appid") String apiKey);

    @GET("/data/2.5/forecast?units=metric")
    Call<WeatherForecast> getWeatherForecastByLatLon(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String apiKey);

    @GET("/data/2.5/forecast?units=metric")
    void getWeatherInfo (@Query("lat") String latitude,
                         @Query("lon") String longitude,
                         @Query("cnt") String cnt,
                         @Query("appid") String appid,
                         Callback<CurrentWeatherData> cb);

}
