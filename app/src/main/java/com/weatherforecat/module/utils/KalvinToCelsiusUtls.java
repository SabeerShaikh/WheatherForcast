package com.weatherforecat.module.utils;

public class KalvinToCelsiusUtls {

    public final static double convertKalvinToCelsius = 273.15;

    public static int kalvinToCelsius(double kalvin) {
        return (int) (kalvin - convertKalvinToCelsius);
    }


}

