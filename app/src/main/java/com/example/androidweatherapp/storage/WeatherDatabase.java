package com.example.androidweatherapp.storage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Weather.class}, version = 3)
public abstract class WeatherDatabase extends RoomDatabase {
    private static WeatherDatabase INSTANCE;
    public abstract WeatherDao weatherDao();

    public static WeatherDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), WeatherDatabase.class, "user-database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
}