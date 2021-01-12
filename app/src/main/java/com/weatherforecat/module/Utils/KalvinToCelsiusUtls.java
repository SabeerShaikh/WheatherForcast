package com.weatherforecat.module.Utils;

public class KalvinToCelsiusUtls {

    public final static double convertKalvinToCelsius = 273.15;

    public static int kalvinToCelsius(double kalvin) {
        return (int) (kalvin - convertKalvinToCelsius);
    }


}

