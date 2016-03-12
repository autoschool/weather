package ru.yandex.autoschool.weather.services;

import ru.yandex.autoschool.weather.models.Weather;

/**
 * @author lanwen (Merkushev Kirill)
 */
public interface WeatherService {
    Weather getWeather(String city, String region);
}
