package ru.yandex.autoschool.weather;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import ru.yandex.autoschool.weather.clients.OpenWeatherClient;

@SpringBootApplication
@PropertySource(
        ignoreResourceNotFound = true,
        value = {
                "classpath:owm.properties",
                "file:/etc/weather/server/owm.properties"
        }
)
@EnableCaching
public class WeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

    @Bean
    @Profile("prod")
    public OpenWeatherClient openWeatherClient(@Value("${owm.token}") String token,
                                               ObjectMapper mapper,
                                               CounterService counter) {
        return OpenWeatherClient.connect(token, mapper, counter);
    }

    @Bean
    public Module jaxbJson() {
        return new JaxbAnnotationModule();
    }
}
