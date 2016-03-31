package ru.yandex.autoschool.weather;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * eroshenkoam
 * 29/10/14
 */
@ApplicationPath("/api")
public class WeatherApplication extends ResourceConfig {
    public WeatherApplication() {
        packages(WeatherApplication.class.getPackage().getName());
    }
}