package com.example.androidweatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidweatherapp.api.models.currentweatherdatalist.CurrentWeatherDataList;
import com.example.androidweatherapp.R;
import com.example.androidweatherapp.viewholders.WeatherCardViewHolder;
import com.example.androidweatherapp.util.WeatherImageHelper;

public class WeatherCardAdapter extends RecyclerView.Adapter<WeatherCardViewHolder> {

    CurrentWeatherDataList data;
    Context context;
    WeatherImageHelper helper;

    public WeatherCardAdapter(Context context, CurrentWeatherDataList data) {
        this.helper = new WeatherImageHelper();
        this.context = context;
        this.data = data;;
    }


    @NonNull
    @Override
    public WeatherCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout weatherCard = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.weathercard, parent, false);
        TextView city = weatherCard.findViewById(R.id.cityNameWeatherCard);
        TextView weatherDescription = weatherCard.findViewById(R.id.weatherDescriptionWeatherCard);
        TextView currentTemp = weatherCard.findViewById(R.id.currentTempWeatherCard);
        TextView maxTemp = weatherCard.findViewById(R.id.maxTempWeatherCard);
        TextView minTemp = weatherCard.findViewById(R.id.minTempWeatherCard);
        ImageView weatherImage = weatherCard.findViewById(R.id.weatherImageViewWeatherCard);
        return new WeatherCardViewHolder(weatherCard, city, weatherDescription, currentTemp, maxTemp, minTemp, weatherImage, context);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherCardViewHolder holder, int position) {
        holder.setCurrentWeatherData(data.getList().get(position));
        holder.setWeatherImage(context.getResources().getDrawable(helper.getWeatherImage(data.getList().get(position).getWeather().get(0).getMain())));
    }


    @Override
    public int getItemCount() {
        return data.getList().size();
    }
}
