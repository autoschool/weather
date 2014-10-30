package ru.yandex.autoschool.weather.models;

import static ru.yandex.autoschool.weather.utils.TemperatureConverter.*;

/**
 * eroshenkoam
 * 29/10/14
 */
public enum WeatherMeasure {

    KELVIN("°K") {
        public double toCelsius(double value) {
            return kelvinToCelsius(value);
        }

        public double toKelvin(double value) {
            return value;
        }
    },

    CELSIUS("°C") {
        public double toCelsius(double value) {
            return value;
        }

        public double toKelvin(double value) {
            return celsiusToKelvin(value);
        }

    };

    private String abbreviation;

    WeatherMeasure(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public abstract double toCelsius(double value);

    public abstract double toKelvin(double value);
}
