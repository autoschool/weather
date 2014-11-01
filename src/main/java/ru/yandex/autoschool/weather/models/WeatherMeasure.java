package ru.yandex.autoschool.weather.models;

import static ru.yandex.autoschool.weather.utils.TemperatureConverter.*;

/**
 * eroshenkoam
 * 29/10/14
 */
public enum WeatherMeasure {
	// all conversions through Kelvin, couse there can be too 
	// many direct conversions
	
    KELVIN(Weather.SCALE_TYPE_KELVIN) {
        public double toKelvin(double value) {
            return value;
        }
        
        public double toCelsius(double value) {
            return kelvinToCelsius(value);
        }
        
        public double toFahrenheit(double value) {
            return kelvinToFahrenheit(value);
        }
    },

    CELSIUS(Weather.SCALE_TYPE_CELSIUS) {
        public double toCelsius(double value) {
            return value;
        }

        public double toKelvin(double value) {
            return celsiusToKelvin(value);
        }
        
        public double toFahrenheit(double value) {
            return kelvinToFahrenheit(celsiusToKelvin(value));
        }
    },
    
    FAHRENHEIT(Weather.SCALE_TYPE_FAHRENHEIT){
        public double toCelsius(double value) {
            return kelvinToCelsius(fahrenheitToKelvin(value));
        }

        public double toKelvin(double value) {
            return fahrenheitToKelvin(value);
        }

        public double toFahrenheit(double value) {
            return value;
        }
    };

    private String scaleType;

    WeatherMeasure(String scaleType) {
        this.scaleType = scaleType;
    }

    public String getAbbreviation() {
        return "°" + scaleType;
    }

    public abstract double toCelsius(double value);

    public abstract double toKelvin(double value);
    
    public abstract double toFahrenheit(double value);
}
