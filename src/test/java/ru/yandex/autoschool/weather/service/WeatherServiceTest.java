package ru.yandex.autoschool.weather.service;

import org.junit.Test;
import ru.yandex.autoschool.weather.clients.OpenWeatherClient;
import ru.yandex.autoschool.weather.clients.OpenWeatherResponse;
import ru.yandex.autoschool.weather.clients.OpenWeatherTemperature;
import ru.yandex.autoschool.weather.models.Weather;
import ru.yandex.autoschool.weather.services.OpenWeatherService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * eroshenkoam
 * 30/10/14
 */
public class WeatherServiceTest {

    private String city = "Saint Petersburg";
    private String region = "ru";
    private double temperature = 280;

    @Test
    public void testWeatherService() {
        OpenWeatherService service = new OpenWeatherService(getMock(city, region, temperature));
        Weather weather = service.getWeather(city, region);

        assertThat(weather, notNullValue());
        assertThat(weather.getCity(), equalTo(city));
    }

    public static OpenWeatherClient getMock(String city, String region, double temperature) {

        OpenWeatherResponse response = new OpenWeatherResponse();
        response.withCity(city)
                .withTemperature(
                        new OpenWeatherTemperature()
                                .withValue(temperature)
                );

        OpenWeatherClient client = mock(OpenWeatherClient.class);
        when(client.getWeather(String.format("%s,%s", city, region), OpenWeatherClient.APP_ID)).thenReturn(response);
        return client;
    }
}
