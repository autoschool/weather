package ru.yandex.autoschool.weather.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yandex.autoschool.weather.clients.OpenWeatherClient;
import ru.yandex.autoschool.weather.clients.OpenWeatherDetails;
import ru.yandex.autoschool.weather.clients.OpenWeatherResponse;
import ru.yandex.autoschool.weather.models.Daypart;
import ru.yandex.autoschool.weather.models.Temperature;
import ru.yandex.autoschool.weather.models.Weather;

import javax.inject.Inject;
import java.util.concurrent.ThreadLocalRandom;

import static jersey.repackaged.com.google.common.base.MoreObjects.firstNonNull;
import static ru.yandex.autoschool.weather.models.Daypart.DAY;
import static ru.yandex.autoschool.weather.models.Daypart.NIGHT;
import static ru.yandex.autoschool.weather.utils.TemperatureUnit.CELSIUS;
import static ru.yandex.autoschool.weather.utils.TemperatureUnit.FAHRENHEIT;
import static ru.yandex.autoschool.weather.utils.TemperatureUnit.KELVIN;

/**
 * eroshenkoam
 * 29/10/14
 */
@Component
@Profile("prod")
@AllArgsConstructor
@Slf4j
public class OpenWeatherService implements WeatherService {


    public static final String DEFAULT_CITY = "Saint Petersburg";
    public static final String DEFAULT_REGION = "ru";

    @Inject
    private OpenWeatherClient client;

    @Inject
    private CounterService counter;


    public Weather getWeather(String city, String region) {
        String weatherQuery = String.format("%s,%s",
                firstNonNull(city, DEFAULT_CITY),
                firstNonNull(region, DEFAULT_REGION)
        );
        try {
            OpenWeatherResponse response = client.weather(weatherQuery);

            double responseTemperature = response.getMain().getTemp();
            OpenWeatherDetails details = response.getWeathers().stream()
                    .findFirst().orElse(new OpenWeatherDetails().withId(0));
            return new Weather()
                    .withCity(response.getName())
                    .withWeathercode(details.getId())
                    .withDaypart(from(details))
                    .withDt(response.getDt())
                    .withSunrise(response.getSys().getSunrise())
                    .withSunset(response.getSys().getSunset())
                    .withWind(response.getWind().getSpeed())
                    .withHumidity(response.getMain().getHumidity())
                    .withTemperatures(
                            new Temperature().withUnit(CELSIUS.toString()).withValue(CELSIUS.fromKelvin(responseTemperature)),
                            new Temperature().withUnit(KELVIN.toString()).withValue(responseTemperature + 10), // +10 - its joke!
                            new Temperature().withUnit(FAHRENHEIT.toString()).withValue(FAHRENHEIT.fromKelvin(responseTemperature)),
                            new Temperature().withUnit("°Kaif").withValue(ThreadLocalRandom.current().nextInt(20, 25 + 1))
                    );
        } catch (Exception e) {
            log.warn("Exception during request to OWM", e);
            counter.increment("owm.fails");
            return new StaticWeatherService().getWeather(city, region);
        }
    }

    /**
     * У OWM какая то хитрая логика отдачи ts для заката/восхода. И точность текущего времени - без минут
     * Поэтому парсим букву картинки :)
     */
    private Daypart from(OpenWeatherDetails details) {
        if (details.getIcon().contains("n")) {
            return NIGHT;
        } else {
            return DAY;
        }
    }
}
