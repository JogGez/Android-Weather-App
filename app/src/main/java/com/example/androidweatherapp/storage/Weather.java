package com.example.androidweatherapp.storage;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Weather {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "city_id")
    public String cityId;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "country_code")
    public String countryCode;


}
