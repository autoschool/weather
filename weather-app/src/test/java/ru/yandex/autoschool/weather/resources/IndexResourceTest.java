package ru.yandex.autoschool.weather.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import ru.yandex.autoschool.weather.WeatherApplication;

import javax.inject.Inject;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author lanwen (Merkushev Kirill)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                WeatherApplication.class
        },
        webEnvironment = RANDOM_PORT
)
public class IndexResourceTest {

    @Inject
    private TestRestTemplate service;

    @Test
    public void shouldReturn200OnSuggest() {
        int status = service
                .getForEntity("/api/suggest?query={query}", String.class, "Sai")
                .getStatusCodeValue();
        assertThat(status, is(HttpStatus.OK.value()));
    }
}
