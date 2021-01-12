package com.weatherforecat.module.ui.weatherforecastui.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.weatherforecat.R;
import com.weatherforecat.databinding.ItemDailyForecastBinding;
import com.weatherforecat.domain.model.DailyTemp;

import java.util.ArrayList;
import java.util.List;

public class DailyForecastAdapter extends
        RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder> {

    private List<DailyTemp> dailyTempArrayList = new ArrayList<>();


    public DailyForecastAdapter() {
    }

    public void clearData() {
        dailyTempArrayList.clear();
        notifyDataSetChanged();
    }

    public void appendData(List<DailyTemp> list) {
        this.dailyTempArrayList.addAll(list);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public DailyForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        ItemDailyForecastBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_daily_forecast, parent, false);
        return new DailyForecastViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyForecastViewHolder holder, int position) {
        DailyTemp dailyTemp = dailyTempArrayList.get(position);
        holder.vbinding.setDailyTemp(dailyTemp);


    }

    @Override
    public int getItemCount() {
        return dailyTempArrayList.size();
    }

    public class DailyForecastViewHolder extends RecyclerView.ViewHolder {
        ItemDailyForecastBinding vbinding;

        public DailyForecastViewHolder(ItemDailyForecastBinding binding) {
            super(binding.getRoot());
            this.vbinding = binding;

        }


    }

}
