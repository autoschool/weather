package ru.yandex.autoschool.weather.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.yandex.autoschool.weather.clients.OpenWeatherClient;
import ru.yandex.autoschool.weather.clients.OpenWeatherDetails;
import ru.yandex.autoschool.weather.clients.OpenWeatherResponse;
import ru.yandex.autoschool.weather.clients.OpenWeatherSys;
import ru.yandex.autoschool.weather.clients.OpenWeatherTemperature;
import ru.yandex.autoschool.weather.clients.OpenWeatherWind;
import ru.yandex.autoschool.weather.models.Temperature;
import ru.yandex.autoschool.weather.models.Weather;
import ru.yandex.autoschool.weather.services.OpenWeatherService;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import static org.mockito.Mockito.when;

/**
 * eroshenkoam
 * 30/10/14
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    private static final String CITY = "Saint Petersburg";
    private static final String REGION = "ru";
    private static final double TEMPERATURE = 280;

    @Mock
    private OpenWeatherClient client;

    @Test
    public void shouldProvideCityWeatherFromService() {
        Weather weather = new OpenWeatherService(getMock(CITY, REGION, TEMPERATURE)).getWeather(CITY, REGION);

        assertThat(weather, notNullValue());
        assertThat(weather.getCity(), equalTo(CITY));
    }

    @Test
    public void shouldReturnKelvinPlus10Degree() {
        Weather weather = new OpenWeatherService(getMock(CITY, REGION, TEMPERATURE)).getWeather(CITY, REGION);

        assumeThat(weather, notNullValue());
        assertThat(weather.getTemperatures().stream().map(Temperature::getValue).collect(Collectors.toList()),
                hasItem(equalTo(TEMPERATURE + 10)));
    }

    public OpenWeatherClient getMock(String city, String region, double temperature) {
        OpenWeatherResponse response = new OpenWeatherResponse()
                .withCity(city)
                .withSys(new OpenWeatherSys())
                .withWind(new OpenWeatherWind())
                .withDetails(new OpenWeatherDetails().withIcon("17n"))
                .withTemperature(new OpenWeatherTemperature().withValue(temperature));

        when(client.getWeather(String.format("%s,%s", city, region), OpenWeatherClient.APP_ID)).thenReturn(response);
        return client;
    }
}
