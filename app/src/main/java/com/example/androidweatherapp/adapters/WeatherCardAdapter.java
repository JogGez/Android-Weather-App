package com.example.androidweatherapp.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidweatherapp.Data;
import com.example.androidweatherapp.api.models.currentweatherdatalist.CurrentWeatherDataList;
import com.example.androidweatherapp.interfaces.ItemClicked;
import com.example.androidweatherapp.R;
import com.example.androidweatherapp.viewholders.WeatherCardViewHolder;
import com.example.androidweatherapp.util.WeatherImageHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class WeatherCardAdapter extends RecyclerView.Adapter<WeatherCardViewHolder> {

    //temp
    CurrentWeatherDataList data;
    String[] cities;
    String[] description;
    int[] temp; //using same array for current, max and min example
    Context context;
    WeatherImageHelper helper;

    ItemClicked activity;

    public WeatherCardAdapter(Context context, CurrentWeatherDataList data) {
        this.helper = new WeatherImageHelper();
        this.context = context;
        this.data = data;
        this.cities = new String[]{"Volvo", "BMW", "Ford", "Mazda"};
        this.description = new String[]{"Volvo", "BMW", "Ford", "Mazda"};
        this.temp = new int[]{5, 5, 6, 7};
//        this.cities = data.getCities();
//        this.description = data.getDescription();
//        this.temp = data.getTemp();
    }

//    public WeatherCardAdapter(Context context, Data data) {
//        this.helper = new WeatherImageHelper();
//        this.context = context;
//        this.data = data;
//        this.cities = data.getCities();
//        this.description = data.getDescription();
//        this.temp = data.getTemp();
//    }

    @NonNull
    @Override
    public WeatherCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout weatherCard = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.weathercard, parent, false);
        TextView city = weatherCard.findViewById(R.id.cityNameWeatherCard);
        //city.setPaintFlags(city.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView weatherDescription = weatherCard.findViewById(R.id.weatherDescriptionWeatherCard);
        TextView currentTemp = weatherCard.findViewById(R.id.currentTempWeatherCard);
        TextView maxTemp = weatherCard.findViewById(R.id.maxTempWeatherCard);
        TextView minTemp = weatherCard.findViewById(R.id.minTempWeatherCard);
        ImageView weatherImage = weatherCard.findViewById(R.id.weatherImageViewWeatherCard);
        return new WeatherCardViewHolder(weatherCard, city, weatherDescription, currentTemp, maxTemp, minTemp, weatherImage, context);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherCardViewHolder holder, int position) {
        holder.setCityId(data.getList().get(position).getId());
        holder.itemView.setTag(data.getList().get(position).getName());
        holder.setCity(data.getList().get(position).getName() + ", " + data.getList().get(position).getSys().getCountry());
        holder.setWeatherDescription(data.getList().get(position).getWeather().get(0).getDescription());
        holder.setCurrentTemp(new DecimalFormat("#").format(data.getList().get(position).getMain().getTemp()) + "°");
        holder.setMaxTemp(new DecimalFormat("#.#").format(data.getList().get(position).getMain().getTempMax()) + "°");
        holder.setMinTemp(new DecimalFormat("#.#").format(data.getList().get(position).getMain().getTempMin()) + "°");
        holder.setWeatherImage(context.getResources().getDrawable(helper.getWeatherImage(data.getList().get(position).getWeather().get(0).getMain())));
    }

//    @Override
//    public void onBindViewHolder(@NonNull WeatherCardViewHolder holder, int position) {
//        holder.itemView.setTag(cities[position]);
//        holder.setCity(cities[position]);
//        holder.setWeatherDescription(description[position]);
//        holder.setCurrentTemp(temp[position] + "°");
//        holder.setMaxTemp(temp[position] + "°");
//        holder.setMinTemp(temp[position] + "°");
//        holder.setWeatherImage(context.getResources().getDrawable(helper.getWeatherImage(description[position])));
//    }

    @Override
    public int getItemCount() {
        return data.getList().size();
    }
}
