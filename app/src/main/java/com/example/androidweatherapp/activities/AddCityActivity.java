package com.example.androidweatherapp.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidweatherapp.adapters.AddCityAdapter;
import com.example.androidweatherapp.api.WeatherAPI;
import com.example.androidweatherapp.api.WeatherApiInterface;
import com.example.androidweatherapp.api.models.findcity.FindCity;
import com.example.androidweatherapp.interfaces.ItemClicked;
import com.example.androidweatherapp.R;
import com.example.androidweatherapp.storage.Weather;
import com.example.androidweatherapp.storage.WeatherDatabase;
import java.io.IOException;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class AddCityActivity extends AppCompatActivity implements ItemClicked {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter addCityAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<com.example.androidweatherapp.api.models.findcity.List> data =  new ArrayList<>();

    private WeatherDatabase weatherDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        EditText searchCityText = findViewById(R.id.searchCityText);

        ImageView searchView = findViewById(R.id.citySearchView);
        searchView.setImageDrawable(getResources().getDrawable(R.drawable.ic_search_24dp));

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(searchCityText.getText())) {
                    searchForCitySync(searchCityText.getText().toString());
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewAddCity);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        addCityAdapter = new AddCityAdapter(this, data);
        recyclerView.setAdapter(addCityAdapter);

        weatherDatabase = WeatherDatabase.getAppDatabase(this);

    }

    @Override
    public void onItemClicked(String string) {
        String[] out = string.split(",");
        String cityId = out[0];
        String city = out[1];
        String countryCode = out[2].trim();
        addWeather(cityId, city, countryCode);
        finish();
    }

    @Override
    public void onItemClicked(com.example.androidweatherapp.api.models.currentweatherdatalist.List data) {

    }

    // -------------------------------- Database ---------------------------------------------------------------

    public void addWeather(String cityId, String city, String countryCode){
        Weather weather = new Weather();
        weather.cityId = cityId;
        weather.city = city;
        weather.countryCode = countryCode;
        weatherDatabase.weatherDao().insert(weather);
    }

    // -------------------------------- API ---------------------------------------------------------------

    private void searchForCityAsync(String city) {
        Retrofit retrofit = WeatherAPI.retrofitAPI();
        WeatherApiInterface weatherAPIs = retrofit.create(WeatherApiInterface.class);
        Call<FindCity> call = weatherAPIs.searchForCity(city, WeatherAPI.getApiIdentifier());

        // Synchronously Call
        try {
            FindCity findCity = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void searchForCitySync(String city) {
        Retrofit retrofit = WeatherAPI.retrofitAPI();
        WeatherApiInterface weatherAPIs = retrofit.create(WeatherApiInterface.class);
        Call<FindCity> call = weatherAPIs.searchForCity(city, WeatherAPI.getApiIdentifier());

        // Asynchronously Call
        call.enqueue(new Callback<FindCity>() {
            @Override
            public void onResponse(Call<FindCity> call, Response<FindCity> response) {
                if (response.body() != null) {
                    FindCity findCity = response.body();
                    data = (ArrayList<com.example.androidweatherapp.api.models.findcity.List>) findCity.getList();
                    addCityAdapter = new AddCityAdapter(AddCityActivity.this, data);
                    recyclerView.setAdapter(addCityAdapter);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("ERROR", "onFailure: ", t);
            }
        });
    }
}
