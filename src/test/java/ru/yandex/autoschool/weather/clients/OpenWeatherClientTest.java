package ru.yandex.autoschool.weather.clients;

import org.junit.Test;
import ru.yandex.autoschool.weather.utils.ClientsBuilder;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * eroshenkoam
 * 29/10/14
 */
public class OpenWeatherClientTest {

    @Test
    public void testOpenWeatherClientDetails() {
        OpenWeatherClient client = ClientsBuilder.getOpenWeatherService();
        OpenWeatherDetails details = client.getWeather("Saint Petersburg,ru", OpenWeatherClient.APP_ID);

        assertThat(details, notNullValue());
        assertThat(details.getStatus(), notNullValue());
        assertThat(details.getStatus().getTemperature(), notNullValue());
    }
}
