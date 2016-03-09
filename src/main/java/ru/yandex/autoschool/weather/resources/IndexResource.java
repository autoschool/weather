package ru.yandex.autoschool.weather.resources;

import ru.yandex.autoschool.weather.models.Weather;
import ru.yandex.autoschool.weather.services.WeatherService;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * eroshenkoam
 * 29/10/14
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class IndexResource {

    @Inject
    private WeatherService weather;

    @GET
    @Path("/weather")
    public Weather getIndex(@DefaultValue(WeatherService.DEFAULT_CITY)
                            @QueryParam("city") String city,
                            @DefaultValue(WeatherService.DEFAULT_REGION)
                            @QueryParam("region") String region) {
        return weather.getWeather(city, region);
    }
}