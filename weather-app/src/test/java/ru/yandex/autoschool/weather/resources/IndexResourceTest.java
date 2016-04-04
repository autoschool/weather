package ru.yandex.autoschool.weather.resources;

import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import ru.yandex.autoschool.weather.WeatherApplication;

import javax.ws.rs.core.Application;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class IndexResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new WeatherApplication();
    }

    @Test
    public void shouldReturn400OnEmptyCity() {
        int status = target("weather").request().buildGet().invoke().getStatus();
        assertThat(status, is(HttpStatus.BAD_REQUEST_400));
    }
}
