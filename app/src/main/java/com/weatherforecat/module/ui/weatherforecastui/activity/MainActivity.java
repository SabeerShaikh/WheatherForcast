package com.weatherforecat.module.ui.weatherforecastui.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weatherforecat.R;
import com.weatherforecat.config.DI;
import com.weatherforecat.config.WeatherProducationDI;
import com.weatherforecat.databinding.ActivityMainBinding;
import com.weatherforecat.module.ui.homescreen.activity.HomeActivity;
import com.weatherforecat.module.ui.networkcheker.Networkchecker;
import com.weatherforecat.module.ui.weatherforecastui.adapter.DailyForecastAdapter;
import com.weatherforecat.module.ui.weatherforecastui.viewmodel.DailyForecastViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding vBinding;
    private DailyForecastViewModel viewModel;
    private DI di;
    private DailyForecastAdapter dailyForecastAdapter;
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        di = new WeatherProducationDI(new Application());
        vBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vBinding.setCallback(this);
        viewModel = (new ViewModelProvider(MainActivity.this, di.provideViewModelFactory())).get(DailyForecastViewModel.class);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        vBinding.rvDailyTemp.setLayoutManager(mLayoutManager);
        vBinding.rvDailyTemp.setItemAnimator(new DefaultItemAnimator());
        dailyForecastAdapter = new DailyForecastAdapter();

        loadDailyForecast();
    }

    private void showloader(boolean show) {
        int visibility = show ? View.INVISIBLE : View.VISIBLE;

        vBinding.pbForecast.setVisibility(show ? View.VISIBLE : View.GONE);
        vBinding.rvDailyTemp.setVisibility(visibility);
        vBinding.loCurrent.setVisibility(visibility);
        vBinding.vDivider.setVisibility(visibility);
    }

    public void loadDailyForecast() {
        showloader(true);
        if (Networkchecker.isNetworkAvailable(this)) {
            viewModel.getAllDailyForecast(12.98, 77.6).observe(this, vmResponse -> {
                showloader(false);
                if (vmResponse.success) {
                    if (vmResponse.response != null) {
                        dailyForecastAdapter.appendData(vmResponse.response.daily.subList(1, 5));
                        vBinding.setCurrent(vmResponse.response.current);
                    }
                }
            });
            vBinding.rvDailyTemp.setAdapter(dailyForecastAdapter);

        } else {
            showloader(false);
            startActivity(HomeActivity.getIntent(this));
            finish();
        }
    }
}