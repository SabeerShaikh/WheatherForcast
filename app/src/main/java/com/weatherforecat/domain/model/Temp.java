package com.weatherforecat.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Temp implements Serializable {
    @SerializedName("day")
    public double day;
    @SerializedName("min")
    public double min;
    @SerializedName("max")
    public double max;
    @SerializedName("night")
    public double night;
    @SerializedName("eve")
    public double eve;
    @SerializedName("morn")
    public double morn;
}
