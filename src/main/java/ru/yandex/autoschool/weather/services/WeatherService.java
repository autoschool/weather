package ru.yandex.autoschool.weather.services;

import ru.yandex.autoschool.weather.clients.OpenWeatherClient;
import ru.yandex.autoschool.weather.clients.OpenWeatherResponse;
import ru.yandex.autoschool.weather.models.Temperature;
import ru.yandex.autoschool.weather.models.Weather;
import ru.yandex.autoschool.weather.utils.ClientsBuilder;

import static jersey.repackaged.com.google.common.base.MoreObjects.firstNonNull;
import static ru.yandex.autoschool.weather.utils.TemperatureConverter.kelvinToCelsius;
import static ru.yandex.autoschool.weather.utils.TemperatureConverter.kelvinToFahrenheit;

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
                firstNonNull(city, DEFAULT_CITY),
                firstNonNull(region, DEFAULT_REGION));

        OpenWeatherResponse response = service.getWeather(weatherQuery, OpenWeatherClient.APP_ID);

        String responseCity = response.getCity();
        if (responseCity == null) {
            return new Weather();
        }

        double responseTemperature = response.getTemperature().getValue();
        return new Weather()
                .withCity(responseCity)
                .withTemperatures(
                        new Temperature().withUnit("°C").withValue(kelvinToCelsius(responseTemperature)),
                        new Temperature().withUnit("°K").withValue(responseTemperature),
                        new Temperature().withUnit("°F").withValue(kelvinToFahrenheit(responseTemperature))
                );
    }
}
