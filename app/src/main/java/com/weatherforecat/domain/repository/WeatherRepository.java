package com.weatherforecat.domain.repository;

import androidx.lifecycle.MutableLiveData;

import com.weatherforecat.domain.RepositoryResponse;
import com.weatherforecat.domain.model.WeatherForecast;

public interface WeatherRepository {
    MutableLiveData<RepositoryResponse<WeatherForecast>> getAllWeatherForecast(double lat, double log);

}
