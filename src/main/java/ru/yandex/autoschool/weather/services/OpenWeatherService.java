package ru.yandex.autoschool.weather.services;

import ru.yandex.autoschool.weather.clients.OpenWeatherClient;
import ru.yandex.autoschool.weather.clients.OpenWeatherDetails;
import ru.yandex.autoschool.weather.clients.OpenWeatherResponse;
import ru.yandex.autoschool.weather.models.Daypart;
import ru.yandex.autoschool.weather.models.Temperature;
import ru.yandex.autoschool.weather.models.Weather;
import ru.yandex.autoschool.weather.utils.ClientsBuilder;

import static java.time.Instant.ofEpochSecond;
import static jersey.repackaged.com.google.common.base.MoreObjects.firstNonNull;
import static ru.yandex.autoschool.weather.models.Daypart.DAY;
import static ru.yandex.autoschool.weather.models.Daypart.NIGHT;
import static ru.yandex.autoschool.weather.utils.TemperatureConverter.kelvinToCelsius;
import static ru.yandex.autoschool.weather.utils.TemperatureConverter.kelvinToFahrenheit;

/**
 * eroshenkoam
 * 29/10/14
 */
public class OpenWeatherService implements WeatherService {

    public static final String DEFAULT_CITY = "Saint Petersburg";
    public static final String DEFAULT_REGION = "ru";

    private OpenWeatherClient service;

    public OpenWeatherService() {
        this.service = ClientsBuilder.getOpenWeatherService();
    }

    public OpenWeatherService(OpenWeatherClient service) {
        this.service = service;
    }

    public Weather getWeather(String city, String region) {
        String weatherQuery = String.format("%s,%s",
                firstNonNull(city, DEFAULT_CITY),
                firstNonNull(region, DEFAULT_REGION));

        OpenWeatherResponse response = service.getWeather(weatherQuery, OpenWeatherClient.APP_ID);

        double responseTemperature = response.getTemperature().getValue();
        return new Weather()
                .withCity(response.getCity())
                .withWeathercode(response.getDetails().stream()
                        .findFirst().orElse(new OpenWeatherDetails().withId(0)).getId())
                .withDaypart(
                        ofEpochSecond(response.getSys().getSunrise()).isBefore(ofEpochSecond(response.getSys().getSunset())) 
                        ? NIGHT 
                        : DAY
                )
                .withDt(response.getDt())
                .withSunrise(response.getSys().getSunrise())
                .withSunset(response.getSys().getSunset())
                .withWind(response.getWind().getSpeed())
                .withHumidity(response.getTemperature().getHumidity())
                .withTemperatures(
                        new Temperature().withUnit("°C").withValue(kelvinToCelsius(responseTemperature)),
                        new Temperature().withUnit("°K").withValue(responseTemperature),
                        new Temperature().withUnit("°F").withValue(kelvinToFahrenheit(responseTemperature))
                );
    }
}
