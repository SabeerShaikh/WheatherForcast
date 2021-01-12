package com.weatherforecat.module.ui.homescreen.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.weatherforecat.R;
import com.weatherforecat.databinding.ActivityHomeBinding;
import com.weatherforecat.module.ui.networkcheker.Networkchecker;
import com.weatherforecat.module.ui.weatherforecastui.activity.MainActivity;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding vBinding;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        vBinding.setCallback(this);
    }

    public void onRetryClicked() {
        if (Networkchecker.isNetworkAvailable(this)) {
            startActivity(MainActivity.getIntent(this));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}