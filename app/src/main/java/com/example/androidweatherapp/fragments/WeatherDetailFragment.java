package com.example.androidweatherapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import com.example.androidweatherapp.R;
import com.example.androidweatherapp.activities.MainActivity;
import com.example.androidweatherapp.api.WeatherAPI;
import com.example.androidweatherapp.api.WeatherApiInterface;
import com.example.androidweatherapp.api.models.currentweatherdatalist.List;
import com.example.androidweatherapp.api.models.forecastweatherdata.WeatherForecast;
import com.example.androidweatherapp.storage.Weather;
import com.example.androidweatherapp.storage.WeatherDatabase;
import com.example.androidweatherapp.util.WeatherImageHelper;
import java.text.DecimalFormat;
import java.time.LocalDate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherDetailFragment extends Fragment {

    private List data;
    private View view;
    private ImageView delete;
    private View includeView;
    private TextView city;
    private TextView weatherDescription;
    private TextView currentTemp;
    private TextView maxTemp;
    private TextView minTemp;
    private ImageView weatherImage;

    private TextView Day1Text;
    private TextView Day2Text;
    private TextView Day3Text;
    private TextView Day4Text;
    private TextView Day5Text;

    private ImageView Day1Image;
    private ImageView Day2Image;
    private ImageView Day3Image;
    private ImageView Day4Image;
    private ImageView Day5Image;

    private TextView MaxTempDay1;
    private TextView MaxTempDay2;
    private TextView MaxTempDay3;
    private TextView MaxTempDay4;
    private TextView MaxTempDay5;
    private TextView MinTempDay1;
    private TextView MinTempDay2;
    private TextView MinTempDay3;
    private TextView MinTempDay4;
    private TextView MinTempDay5;

    private TextView humidity;
    private TextView wind;
    private TextView cloudiness;
    private TextView pressure;

    private WeatherDatabase weatherDatabase;

    WeatherImageHelper helper;

    public WeatherDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = (List) getArguments().getSerializable("currentData");
        }

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                ((MainActivity)getActivity()).refresh();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_city_weather, container, false);

        weatherDatabase = WeatherDatabase.getAppDatabase(getActivity());

        includeView = view.findViewById(R.id.weatherCardView);
        city = includeView.findViewById(R.id.cityNameWeatherCard);
        weatherDescription = includeView.findViewById(R.id.weatherDescriptionWeatherCard);
        currentTemp = includeView.findViewById(R.id.currentTempWeatherCard);
        maxTemp = includeView.findViewById(R.id.maxTempWeatherCard);
        minTemp = includeView.findViewById(R.id.minTempWeatherCard);
        weatherImage = includeView.findViewById(R.id.weatherImageViewWeatherCard);

        Day1Text = view.findViewById(R.id.textViewDay1);
        Day2Text = view.findViewById(R.id.textViewDay2);
        Day3Text = view.findViewById(R.id.textViewDay3);
        Day4Text = view.findViewById(R.id.textViewDay4);
        Day5Text = view.findViewById(R.id.textViewDay5);

        Day1Image = view.findViewById(R.id.imageViewDay1);
        Day2Image = view.findViewById(R.id.imageViewDay2);
        Day3Image = view.findViewById(R.id.imageViewDay3);
        Day4Image = view.findViewById(R.id.imageViewDay4);
        Day5Image = view.findViewById(R.id.imageViewDay5);

        MaxTempDay1 = view.findViewById(R.id.textViewMaxTempDay1);
        MaxTempDay2 = view.findViewById(R.id.textViewMaxTempDay2);
        MaxTempDay3 = view.findViewById(R.id.textViewMaxTempDay3);
        MaxTempDay4 = view.findViewById(R.id.textViewMaxTempDay4);
        MaxTempDay5 = view.findViewById(R.id.textViewMaxTempDay5);
        MinTempDay1 = view.findViewById(R.id.textViewMinTempDay1);
        MinTempDay2 = view.findViewById(R.id.textViewMinTempDay2);
        MinTempDay3 = view.findViewById(R.id.textViewMinTempDay3);
        MinTempDay4 = view.findViewById(R.id.textViewMinTempDay4);
        MinTempDay5 = view.findViewById(R.id.textViewMinTempDay5);

        humidity = view.findViewById(R.id.textViewHumidity);
        wind = view.findViewById(R.id.textViewWind);
        cloudiness = view.findViewById(R.id.textViewCloudiness);
        pressure = view.findViewById(R.id.textViewPressure);

        delete = view.findViewById(R.id.deleteView);
        delete.setImageDrawable(getResources().getDrawable(R.drawable.ic_delete_black_24dp));
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Weather weather = weatherDatabase.weatherDao().getById(data.getId().toString());
                weatherDatabase.weatherDao().delete(weather);
                ((MainActivity)getActivity()).refresh();
                getActivity().onBackPressed();
            }
        });

        helper = new WeatherImageHelper();
        updateCityView(data);

        return view;
    }
    public void updateCityView(List data){
        this.data = data;
        if(data != null){
            city.setText(data.getName() + ", " + data.getSys().getCountry());
            weatherDescription.setText(data.getWeather().get(0).getDescription());
            currentTemp.setText(new DecimalFormat("#").format(data.getMain().getTemp()) + "°");
            maxTemp.setText(new DecimalFormat("#").format(data.getMain().getTempMax()) + "°");
            minTemp.setText(new DecimalFormat("#").format(data.getMain().getTempMin()) + "°");
            weatherImage.setImageDrawable(getContext().getResources().getDrawable(helper.getWeatherImage(data.getWeather().get(0).getMain())));
            getCurrentWeatherDataSync(data.getId().toString());
        }

    }

    private void getCurrentWeatherDataSync(String id) {
        Retrofit retrofit = WeatherAPI.retrofitAPI();
        WeatherApiInterface weatherAPIs = retrofit.create(WeatherApiInterface.class);
        Call<WeatherForecast> call = weatherAPIs.getWeatherForecastById(id, WeatherAPI.getApiIdentifier());
        // Asynchronously Call
        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                if (response.body() != null) {
                    WeatherForecast weatherForecast = response.body();

                    LocalDate date = LocalDate.now();
                    date = date.plusDays(1);
                    Day1Text.setText(date.getDayOfWeek().toString().substring(0,2));
                    date = date.plusDays(1);
                    Day2Text.setText(date.getDayOfWeek().toString().substring(0,2));
                    date = date.plusDays(1);
                    Day3Text.setText(date.getDayOfWeek().toString().substring(0,2));
                    date = date.plusDays(1);
                    Day4Text.setText(date.getDayOfWeek().toString().substring(0,2));
                    date = date.plusDays(1);
                    Day5Text.setText(date.getDayOfWeek().toString().substring(0,2));

                    Day1Image.setImageDrawable(getContext().getResources().getDrawable(helper.getWeatherImage(weatherForecast.getList().get(3).getWeather().get(0).getMain())));
                    Day2Image.setImageDrawable(getContext().getResources().getDrawable(helper.getWeatherImage(weatherForecast.getList().get(11).getWeather().get(0).getMain())));
                    Day3Image.setImageDrawable(getContext().getResources().getDrawable(helper.getWeatherImage(weatherForecast.getList().get(19).getWeather().get(0).getMain())));
                    Day4Image.setImageDrawable(getContext().getResources().getDrawable(helper.getWeatherImage(weatherForecast.getList().get(27).getWeather().get(0).getMain())));
                    Day5Image.setImageDrawable(getContext().getResources().getDrawable(helper.getWeatherImage(weatherForecast.getList().get(35).getWeather().get(0).getMain())));

                    MaxTempDay1.setText(new DecimalFormat("#").format(weatherForecast.getList().get(3).getMain().getTempMax()) + "°");
                    MaxTempDay2.setText(new DecimalFormat("#").format(weatherForecast.getList().get(11).getMain().getTempMax()) + "°");
                    MaxTempDay3.setText(new DecimalFormat("#").format(weatherForecast.getList().get(19).getMain().getTempMax()) + "°");
                    MaxTempDay4.setText(new DecimalFormat("#").format(weatherForecast.getList().get(27).getMain().getTempMax()) + "°");
                    MaxTempDay5.setText(new DecimalFormat("#").format(weatherForecast.getList().get(35).getMain().getTempMax()) + "°");
                    MinTempDay1.setText(new DecimalFormat("#").format(weatherForecast.getList().get(3).getMain().getTempMin()) + "°");
                    MinTempDay2.setText(new DecimalFormat("#").format(weatherForecast.getList().get(11).getMain().getTempMin()) + "°");
                    MinTempDay3.setText(new DecimalFormat("#").format(weatherForecast.getList().get(19).getMain().getTempMin()) + "°");
                    MinTempDay4.setText(new DecimalFormat("#").format(weatherForecast.getList().get(27).getMain().getTempMin()) + "°");
                    MinTempDay5.setText(new DecimalFormat("#").format(weatherForecast.getList().get(35).getMain().getTempMin()) + "°");

                    humidity.setText(weatherForecast.getList().get(0).getMain().getHumidity() + "%");
                    wind.setText(weatherForecast.getList().get(0).getWind().getSpeed() + "m/s");
                    cloudiness.setText(weatherForecast.getList().get(0).getClouds().getAll() + "%");
                    pressure.setText(weatherForecast.getList().get(0).getMain().getPressure() + "hPa");

                }
            }



            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("ERROR", "onFailure: ", t);
            }
        });
    }
}
