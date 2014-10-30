package ru.yandex.autoschool.weather.services;

import jersey.repackaged.com.google.common.base.Objects;
import ru.yandex.autoschool.weather.clients.OpenWeatherClient;
import ru.yandex.autoschool.weather.models.Weather;
import ru.yandex.autoschool.weather.models.WeatherMeasure;
import ru.yandex.autoschool.weather.clients.OpenWeatherDetails;
import ru.yandex.autoschool.weather.utils.ClientsBuilder;

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

    public Weather getWeather(String city, String region) {
        String weatherQuery = String.format("%s,%s",
                Objects.firstNonNull(city, DEFAULT_CITY),
                Objects.firstNonNull(region, DEFAULT_REGION));

        OpenWeatherDetails response = service.getWeather(weatherQuery);
        Weather weather = new Weather();
        weather.setTemperature(WeatherMeasure.KELVIN.toCelsius(response.getStatus().getTemperature()));
        weather.setMeasure(WeatherMeasure.CELSIUS);
        weather.setCity(response.getCity());
        return weather;
    }
}
