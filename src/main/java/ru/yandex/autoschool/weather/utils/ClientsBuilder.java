package ru.yandex.autoschool.weather.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.RestAdapter;
import ru.yandex.autoschool.weather.clients.OpenWeatherClient;

/**
 * eroshenkoam
 * 29/10/14
 */
public class ClientsBuilder {
    
    public static Logger LOG = LoggerFactory.getLogger(OpenWeatherClient.class);

    public static OpenWeatherClient getOpenWeatherService() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(LOG::info)
                .setEndpoint("http://api.openweathermap.org")
                .build();
        return adapter.create(OpenWeatherClient.class);
    }
}
