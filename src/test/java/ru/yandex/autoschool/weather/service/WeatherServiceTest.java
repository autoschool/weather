package ru.yandex.autoschool.weather.service;

import org.junit.Test;
import org.mockito.internal.matchers.Any;
import ru.yandex.autoschool.weather.clients.OpenWeatherClient;
import ru.yandex.autoschool.weather.clients.OpenWeatherDetails;
import ru.yandex.autoschool.weather.clients.OpenWeatherStatus;
import ru.yandex.autoschool.weather.models.Weather;
import ru.yandex.autoschool.weather.models.WeatherMeasure;
import ru.yandex.autoschool.weather.services.WeatherService;

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
        WeatherService service = new WeatherService(getMock(city, region, temperature));
        Weather weather = service.getWeather(city, region);

        assertThat(weather, notNullValue());
        assertThat(weather.getCity(), equalTo(city));
        assertThat(weather.getTemperature(), equalTo(WeatherMeasure.KELVIN.toCelsius(temperature)));
    }

    public static OpenWeatherClient getMock(String city, String region, double temperature) {

        OpenWeatherStatus status = new OpenWeatherStatus();
        status.setTemperature(temperature);
        OpenWeatherDetails details = new OpenWeatherDetails();
        details.setCity(city);
        details.setStatus(status);


        OpenWeatherClient client = mock(OpenWeatherClient.class);
        when(client.getWeather(String.format("%s,%s", city, region))).thenReturn(details);
        return client;
    }
}
