package com.weatherforecat.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DailyTemp implements Serializable {
    @SerializedName("dt")
    public int dt;
    @SerializedName("sunrise")
    public int sunrise;
    @SerializedName("sunset")
    public int sunset;
    @SerializedName("temp")
    public Temp temp;
    @SerializedName("feels_like")
    public FeelsLike feels_like;
    @SerializedName("pressure")
    public int pressure;
    @SerializedName("humidity")
    public int humidity;
    @SerializedName("dew_point")
    public double dew_point;
    @SerializedName("wind_speed")
    public double wind_speed;
    @SerializedName("wind_deg")
    public int wind_deg;


}
