package ru.yandex.autoschool.weather.clients;

import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * eroshenkoam
 * 29/10/14
 */
public class OpenWeatherClientTest {

    @Test
    public void testOpenWeatherClientDetails() {
        OpenWeatherClient client = OpenWeatherClient.getOpenWeatherService();
        OpenWeatherResponse weatherResponse = client.getWeather("Saint Petersburg,ru", OpenWeatherClient.APP_ID);

        assertThat(weatherResponse, notNullValue());
        assertThat(weatherResponse.getTemperature(), notNullValue());
        assertThat(weatherResponse.getTemperature().getValue(), notNullValue());
    }
}
