package com.weatherforecat.domain.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.weatherforecat.BuildConfig;
import com.weatherforecat.domain.RepositoryResponse;
import com.weatherforecat.domain.model.WeatherForecast;
import com.weatherforecat.domain.repository.remote.APIErrorResponse;
import com.weatherforecat.domain.repository.remote.api.WeatherService.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepositoryImpl implements WeatherRepository {


    private WeatherService service;

    public WeatherRepositoryImpl(WeatherService fileService) {
        this.service = fileService;
    }

    @Override
    public MutableLiveData<RepositoryResponse<WeatherForecast>> getAllWeatherForecast(double lat, double log) {
        MutableLiveData<RepositoryResponse<WeatherForecast>> result = new MutableLiveData<>();
        service.getAllWeatherData(lat, log, "", BuildConfig.API_ID).enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                Log.d("DataDailyForcast", "" + response.body().timezone);
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(new RepositoryResponse<>(response.body()));

                } else {
                    APIErrorResponse apiErrorResponse = service.errorResponseHandler(response.errorBody());
                    result.setValue(new RepositoryResponse<WeatherForecast>(apiErrorResponse.getError(),
                            apiErrorResponse.getStatusCode()));
                    return;
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                result.setValue(new RepositoryResponse<WeatherForecast>(t.getMessage(),
                        401));

            }
        });
        return result;
    }
}
