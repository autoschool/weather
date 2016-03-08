package ru.yandex.autoschool.weather.services;

import ru.yandex.autoschool.weather.clients.OpenWeatherClient;
import ru.yandex.autoschool.weather.clients.OpenWeatherResponse;
import ru.yandex.autoschool.weather.models.Weather;
import ru.yandex.autoschool.weather.models.WeatherMeasure;
import ru.yandex.autoschool.weather.utils.ClientsBuilder;

import static jersey.repackaged.com.google.common.base.MoreObjects.firstNonNull;

/**
 * eroshenkoam
 * 29/10/14
 */
public class WeatherService {

    public static final String DEFAULT_CITY = "Saint Petersburg";
    public static final String DEFAULT_REGION = "ru";

    private OpenWeatherClient service;

    public WeatherService() {
        this.service = ClientsBuilder.getOpenWeatherService();
    }

    public WeatherService(OpenWeatherClient service) {
        this.service = service;
    }

    public Weather getWeather(String city, String region, String scale) {
        String weatherQuery = String.format("%s,%s",
                firstNonNull(city, DEFAULT_CITY),
                firstNonNull(region, DEFAULT_REGION));

        OpenWeatherResponse response = service.getWeather(weatherQuery, OpenWeatherClient.APP_ID);
        Weather weather = new Weather();

        if (response.getCity() == null) {
            return weather;
        }

        if (scale == null) {
            scale = Weather.SCALE_TYPE_CELSIUS;
        }

        double temperature = response.getTemperature().getValue();

        switch (scale) {
            case Weather.SCALE_TYPE_KELVIN:
                weather.setTemperature(WeatherMeasure.KELVIN, temperature);
                break;
            case Weather.SCALE_TYPE_FAHRENHEIT:
                weather.setTemperature(WeatherMeasure.FAHRENHEIT, temperature);
                break;
            default:
                weather.setTemperature(WeatherMeasure.CELSIUS,
                        WeatherMeasure.KELVIN.toCelsius(temperature));
                break;
        }

        weather.setCity(response.getCity());
        return weather;
    }
}
