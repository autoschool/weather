package ru.yandex.autoschool.weather.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import ru.yandex.autoschool.weather.rest.resources.IndexResource;

import javax.ws.rs.ApplicationPath;

/**
 * eroshenkoam
 * 29/10/14
 */
@ApplicationPath("/api")
@Component
public class WeatherResourceConfig extends ResourceConfig {
    public WeatherResourceConfig() {
        register(IndexResource.class);
    }
}
