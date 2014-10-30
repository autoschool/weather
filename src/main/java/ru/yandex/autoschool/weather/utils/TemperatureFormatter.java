package ru.yandex.autoschool.weather.utils;

/**
 * eroshenkoam
 * 29/10/14
 */
public class TemperatureFormatter {

    public static String humanise(double temperature, String measure) {
        String sign = temperature < 0 ? "-" : "+";
        return String.format("%s%s %s", sign, (int) Math.round(temperature), measure);
    }
}
