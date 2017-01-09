package ru.yandex.autoschool.weather.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yandex.autoschool.weather.models.Temperature;
import ru.yandex.autoschool.weather.models.Weather;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static ru.yandex.autoschool.weather.models.Daypart.DAY;
import static ru.yandex.autoschool.weather.utils.TemperatureUnit.CELSIUS;
import static ru.yandex.autoschool.weather.utils.TemperatureUnit.FAHRENHEIT;
import static ru.yandex.autoschool.weather.utils.TemperatureUnit.KELVIN;

/**
 * eroshenkoam
 * 29/10/14
 */
@Component
@Profile("dev")
public class StaticWeatherService implements WeatherService {

    public Weather getWeather(String city, String region) {
        double responseTemperature = ThreadLocalRandom.current().nextInt(-1, 5 + 1);
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
                        new Temperature().withUnit(CELSIUS.toString()).withValue(CELSIUS.fromKelvin(responseTemperature)),
                        new Temperature().withUnit(KELVIN.toString()).withValue(responseTemperature),
                        new Temperature().withUnit(FAHRENHEIT.toString()).withValue(FAHRENHEIT.fromKelvin(responseTemperature))
                );
    }
}
