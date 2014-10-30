package ru.yandex.autoschool.weather.models;

import static ru.yandex.autoschool.weather.utils.TemperatureFormatter.humanise;

/**
 * eroshenkoam
 * 29/10/14
 */
public class Weather {

    private WeatherMeasure measure;

    private double temperature;

    private String city;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public WeatherMeasure getMeasure() {
        return measure;
    }

    public void setMeasure(WeatherMeasure measure) {
        this.measure = measure;
    }

    public String getHumanisedTemperature() {
        return humanise(temperature, measure.getAbbreviation());
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
