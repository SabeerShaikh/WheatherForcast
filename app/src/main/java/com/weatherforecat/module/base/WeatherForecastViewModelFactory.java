package com.weatherforecat.module.base;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.weatherforecat.module.ui.weatherforecastui.viewmodel.DailyForecastViewModel;

import org.jetbrains.annotations.NotNull;


public class WeatherForecastViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;

    public WeatherForecastViewModelFactory(Application application) {
        mApplication = application;
    }

    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {

        if (modelClass == DailyForecastViewModel.class) {
            return (T) new DailyForecastViewModel(mApplication);
        }
        return super.create(modelClass);
    }
}
