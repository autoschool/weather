package ru.yandex.autoschool.weather.utils;

import retrofit.RestAdapter;
import ru.yandex.autoschool.weather.clients.OpenWeatherClient;

/**
 * eroshenkoam
 * 29/10/14
 */
public class ClientsBuilder {

    public static OpenWeatherClient getOpenWeatherService() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .build();
        return adapter.create(OpenWeatherClient.class);
    }
}
