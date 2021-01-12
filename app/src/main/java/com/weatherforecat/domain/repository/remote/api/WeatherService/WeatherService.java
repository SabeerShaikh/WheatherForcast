package com.weatherforecat.domain.repository.remote.api.WeatherService;

import com.weatherforecat.domain.model.WeatherForecast;
import com.weatherforecat.domain.repository.remote.APIService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService extends APIService {

    @GET("onecall")
    Call<WeatherForecast> getAllWeatherData(@Query("lat") double lat, @Query("lon") double log, @Query("exclude") String exclude, @Query("appid") String appid);
}
