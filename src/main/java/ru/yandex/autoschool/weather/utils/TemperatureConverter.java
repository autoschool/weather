package ru.yandex.autoschool.weather.utils;

/**
 * eroshenkoam
 * 29/10/14
 */
public class TemperatureConverter {

    public static double celsiusToKelvin(double value) {
        return value + 273.15;
    }

    public static double kelvinToCelsius(double value) {
        return value - 273.15;
    }

}
