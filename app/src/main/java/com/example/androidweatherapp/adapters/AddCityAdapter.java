package com.example.androidweatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidweatherapp.R;
import com.example.androidweatherapp.api.models.findcity.List;
import com.example.androidweatherapp.viewholders.AddCityViewHolder;

import java.util.ArrayList;

public class AddCityAdapter extends RecyclerView.Adapter<AddCityViewHolder>{

    private Context context;
    private ArrayList<com.example.androidweatherapp.api.models.findcity.List> data;

    public AddCityAdapter(Context context, ArrayList<com.example.androidweatherapp.api.models.findcity.List> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AddCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout addCityCard = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.searchcitycard, parent, false);
        TextView cityNameTextView = addCityCard.findViewById(R.id.cityNameTextView);
        AddCityViewHolder addCityViewHolder = new AddCityViewHolder(addCityCard, cityNameTextView, context);
        return addCityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddCityViewHolder holder, int position) {
            holder.itemView.setTag(data.get(position));
            holder.setCityName(data.get(position).getName() + ", " + data.get(position).getSys().getCountry());
            holder.setCityId(data.get(position).getId().toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
