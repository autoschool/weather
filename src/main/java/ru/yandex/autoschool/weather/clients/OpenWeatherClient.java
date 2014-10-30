package ru.yandex.autoschool.weather.clients;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * eroshenkoam
 * 29/10/14
 */
public interface OpenWeatherClient {

    @GET("/data/2.5/weather")
    OpenWeatherDetails getWeather(@Query("q") String query);
}
