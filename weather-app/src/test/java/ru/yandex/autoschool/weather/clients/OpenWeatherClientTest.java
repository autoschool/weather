package ru.yandex.autoschool.weather.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * eroshenkoam
 * 29/10/14
 */
public class OpenWeatherClientTest {

    @Test
    @Ignore
    public void testOpenWeatherClientDetails() {
        OpenWeatherClient client = OpenWeatherClient.connect("123", new ObjectMapper());
        OpenWeatherResponse weatherResponse = client.weather("Saint Petersburg,ru");

        assertThat(weatherResponse, notNullValue());
        assertThat(weatherResponse.getTemperature(), notNullValue());
        assertThat(weatherResponse.getTemperature().getValue(), notNullValue());
    }
}
