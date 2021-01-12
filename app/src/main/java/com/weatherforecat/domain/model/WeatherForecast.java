package com.weatherforecat.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherForecast implements Serializable {
    @SerializedName("ID")
    public double ID;
    @SerializedName("lon")
    public double lon;
    @SerializedName("timezone")
    public String timezone;
    @SerializedName("timezone_offset")
    public int timezone_offset;
    @SerializedName("current")
    public Current current;
    @SerializedName("daily")
    public List<DailyTemp> daily;
}
