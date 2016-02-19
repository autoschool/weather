package ru.yandex.autoschool.weather.utils;

/**
 * eroshenkoam
 * 29/10/14
 */
public class TemperatureConverter {
	// all conversions through Kelvin, couse there can be too 
	// many direct conversions
	
	// Kelvin <-> Celsius section
    public static double celsiusToKelvin(double value) {
        return value + 273.15;
    }
    
    public static double kelvinToCelsius(double value) {
        return value - 273.15;
    }
    
    // Kelvin <-> Fahrenheit section
    public static double fahrenheitToKelvin(double value) {
        return (value - 32)*5/9 + 273.15;
    }
    
    public static double kelvinToFahrenheit(double value) {
        return 1.8*(value - 273.15) + 32;
    }
}
