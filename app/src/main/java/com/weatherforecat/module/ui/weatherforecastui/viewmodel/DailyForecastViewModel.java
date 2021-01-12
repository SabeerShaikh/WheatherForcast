package com.weatherforecat.module.ui.weatherforecastui.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.weatherforecat.domain.model.WeatherForecast;
import com.weatherforecat.domain.repository.WeatherRepository;
import com.weatherforecat.module.base.WFViewModelResponse;
import com.weatherforecat.module.base.WeatherForecastViewModel;

public class DailyForecastViewModel extends WeatherForecastViewModel {
    public WeatherRepository repository;

    public DailyForecastViewModel(@NonNull Application application) {
        super(application);
        repository = di.provideWeatherRepository();

    }

    public LiveData<WFViewModelResponse<WeatherForecast>> getAllDailyForecast(double lat, double log) {

        return Transformations.map(repository.getAllWeatherForecast(lat, log), repoResponse -> {
            Log.d("DataDailyForcast", "-----" + repoResponse.repositoryResponse);

            if (repoResponse.success) {

                return new WFViewModelResponse<WeatherForecast>(repoResponse.repositoryResponse);
            }
            return new WFViewModelResponse<>(repoResponse.failureMessage, repoResponse.code);
        });
    }

}
