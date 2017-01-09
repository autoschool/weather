package ru.yandex.autoschool.weather.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cache.annotation.Cacheable;

/**
 * eroshenkoam
 * 29/10/14
 */
public interface OpenWeatherClient {

    @RequestLine("GET /data/2.5/weather?q={query}")
    @Cacheable("city")
    OpenWeatherResponse weather(@Param("query") String query);

    static OpenWeatherClient connect(String token, ObjectMapper mapper, CounterService counter) {
        return Feign.builder()
                .decoder(new JacksonDecoder(mapper))
                .logger(new Slf4jLogger(OpenWeatherClient.class))
                .logLevel(Logger.Level.FULL)
                .requestInterceptor(template -> template.query("APPID", token))
                .requestInterceptor(template -> counter.increment("owm.calls"))
                .target(OpenWeatherClient.class, "http://api.openweathermap.org");
    }
}

