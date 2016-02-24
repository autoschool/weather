package ru.yandex.autoschool.weather.clients;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * eroshenkoam
 * 29/10/14
 */
public interface OpenWeatherClient {

    String APP_ID = "8d59609d9f7710500ed92e7a199c2d14";

    @GET("/data/2.5/weather")
    OpenWeatherDetails getWeather(@Query("q") String query, @Query("APPID") String appId);
}
