package com.weatherforecat.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Current implements Serializable {

    @SerializedName("dt")
    public int dt;
    @SerializedName("sunrise")
    public int sunrise;
    @SerializedName("sunset")
    public int sunset;
    @SerializedName("temp")
    public double temp;
    @SerializedName("feels_like")
    public double feels_like;

}
