package com.weatherforecat.config;

import android.app.Application;

import com.weatherforecat.domain.repository.WeatherRepository;
import com.weatherforecat.domain.repository.remote.api.WeatherService.WeatherService;
import com.weatherforecat.module.base.WeatherForecastViewModelFactory;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public interface DI {
    /**
     * Repositories
     */
    WeatherRepository provideWeatherRepository();
    /** ENDS Repositories Providers */

    /**
     * API Service Providers
     **/

    WeatherService provideWeatherService();

    /**
     * ENDS API Service Providers
     **/


    Application provideApplication();

    OkHttpClient provideOkHttpClient();

    Retrofit provideRetrofit();

    WeatherForecastViewModelFactory provideViewModelFactory();

}

