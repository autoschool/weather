package ru.yandex.autoschool.weather.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yandex.autoschool.weather.models.Temperature;
import ru.yandex.autoschool.weather.models.Weather;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static ru.yandex.autoschool.weather.models.Daypart.DAY;
import static ru.yandex.autoschool.weather.utils.TemperatureConverter.kelvinToCelsius;
import static ru.yandex.autoschool.weather.utils.TemperatureConverter.kelvinToFahrenheit;

/**
 * eroshenkoam
 * 29/10/14
 */
@Component
@Profile("dev")
public class StaticWeatherService implements WeatherService {

    public Weather getWeather(String city, String region) {
        double responseTemperature = 1.1;
        return new Weather()
                .withCity(isEmpty(city) ? "Default City" : city)
                .withWeathercode(asList(200, 300, 500, 600, 800).get(new Random().nextInt(5)))
                .withDaypart(DAY)
                .withDt(Instant.now().getEpochSecond())
                .withSunrise(Instant.now().plus(12, ChronoUnit.HOURS).getEpochSecond())
                .withSunset(Instant.now().getEpochSecond())
                .withHumidity(new Random().nextInt(100))
                .withWind(11)
                .withTemperatures(
                        new Temperature().withUnit("°C").withValue(kelvinToCelsius(responseTemperature)),
                        new Temperature().withUnit("°K").withValue(responseTemperature),
                        new Temperature().withUnit("°F").withValue(kelvinToFahrenheit(responseTemperature))
                );
    }
}
