package com.example.androidweatherapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidweatherapp.adapters.WeatherCardAdapter;
import com.example.androidweatherapp.R;
import com.example.androidweatherapp.api.WeatherAPI;
import com.example.androidweatherapp.api.WeatherApiInterface;
import com.example.androidweatherapp.api.models.currentweatherdatalist.CurrentWeatherDataList;
import com.example.androidweatherapp.storage.Weather;
import com.example.androidweatherapp.storage.WeatherDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherCardFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter weatherCardAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View view;

    private WeatherDatabase weatherDatabase;

    public WeatherCardFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_weather_card_r_v, container, false);
        weatherDatabase = WeatherDatabase.getAppDatabase(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.weatherCardRView);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(layoutManager);

        getWeather();
    }

    private void getWeather(){
        List<Weather> weather = weatherDatabase.weatherDao().getAll();
        String ids = "";
        for(Weather v : weather){
            ids += v.cityId + ",";
        }
        ids = ids.replaceAll(",$", "");
        getCurrentWeatherDataListSync(ids);
    }

    private void getCurrentWeatherDataListSync(String ids) {
        Retrofit retrofit = WeatherAPI.retrofitAPI();
        WeatherApiInterface weatherAPIs = retrofit.create(WeatherApiInterface.class);
        Call<CurrentWeatherDataList> call = weatherAPIs.getCurrentWeatherByList(ids, WeatherAPI.getApiIdentifier());
        // Asynchronously Call
        call.enqueue(new Callback<CurrentWeatherDataList>() {
            @Override
            public void onResponse(Call<CurrentWeatherDataList> call, Response<CurrentWeatherDataList> response) {
                if (response.body() != null) {
                    CurrentWeatherDataList currentWeatherData = response.body();
                    Log.d("SYNC", "onResponse: ");
                    weatherCardAdapter = new WeatherCardAdapter(getContext(), currentWeatherData);
                    recyclerView.setAdapter(weatherCardAdapter);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("ERROR", "onFailure: ", t);
            }
        });
    }
}
