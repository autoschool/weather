package ru.yandex.autoschool.weather.utils;

/**
 * eroshenkoam
 * 29/10/14
 */
public class TemperatureFormatter {

    public static String humanize(double temperature, String measure) {
        int temp = (int) Math.round(temperature);
        return String.format("%s%s %s", (temp <= 0 ? "" : "+"), temp, measure);
    }
}
