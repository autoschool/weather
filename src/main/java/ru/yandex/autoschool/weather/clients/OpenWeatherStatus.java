package ru.yandex.autoschool.weather.clients;

import com.google.gson.annotations.SerializedName;

/**
 * eroshenkoam
 * 29/10/14
 */
public class OpenWeatherStatus {

    @SerializedName("temp")
    private double temperature;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }


}
