package com.example.androidweatherapp.storage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WeatherDao {
    @Query("SELECT * FROM weather")
    List<Weather> getAll();

    @Query("SELECT * FROM weather WHERE uid IN (:weatherIds)")
    List<Weather> loadAllByIds(int[] weatherIds);

    @Query("SELECT * FROM weather WHERE city LIKE :city AND " +
            "country_code LIKE :countryCode LIMIT 1")
    Weather findByName(String city, String countryCode);

    @Query("SELECT * FROM weather WHERE city_id LIKE :cityId LIMIT 1")
    Weather getById(String cityId);

    @Query("SELECT COUNT(*) from weather")
    int countWeatherEntries();

    @Insert
    void insert(Weather weather);
    @Insert
    void insertAll(Weather... weathers);
    @Update
    void update(Weather weather);
    @Delete
    void delete(Weather weather);
}
