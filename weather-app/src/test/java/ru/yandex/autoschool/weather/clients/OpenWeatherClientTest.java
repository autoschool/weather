package ru.yandex.autoschool.weather.clients;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.hasSize;
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
        OpenWeatherClient client = OpenWeatherClient.connect("4d25b277cef7562ce992dd3cba00f97e",
                new ObjectMapper()
                        .registerModule(new JaxbAnnotationModule())
                        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        );
        OpenWeatherResponse weatherResponse = client.weather("Saint Petersburg,ru");

        assertThat(weatherResponse, notNullValue());
        assertThat(weatherResponse.getName(), notNullValue());
        assertThat(weatherResponse.getMain(), notNullValue());
        assertThat(weatherResponse.getMain().getTemp(), notNullValue());
        assertThat(weatherResponse.getWeathers(), hasSize(1));
        assertThat(weatherResponse.getWeathers().get(0).getIcon(), notNullValue());
    }
}
