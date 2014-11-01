package ru.yandex.autoschool.weather.models;

import static ru.yandex.autoschool.weather.utils.TemperatureFormatter.humanize;

/**
 * eroshenkoam
 * 29/10/14
 */
public class Weather {
	
    public static final String SCALE_TYPE_KELVIN = "K";
    public static final String SCALE_TYPE_CELSIUS = "C";
    public static final String SCALE_TYPE_FAHRENHEIT = "F";

    private WeatherMeasure measure;

    private double temperature;

    private String city;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(WeatherMeasure measure, double temperature) {
        this.measure = measure;
        this.temperature = temperature;
    }

    public String getHumanizedTemperature() {
        return humanize(temperature, measure.getAbbreviation());
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
