package com.weatherforecat.module.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.weatherforecat.config.DI;
import com.weatherforecat.config.WeatherProducationDI;

public class WeatherForecastViewModel extends AndroidViewModel {

    protected static DI di;
    protected static WeatherForecastViewModelFactory vmCommonFactory;
    protected Application application;

    public WeatherForecastViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        if (di == null) {
            di = new WeatherProducationDI(application);
            vmCommonFactory = di.provideViewModelFactory();
        }
    }
}
