package ru.yandex.autoschool.weather.resources;

import org.glassfish.jersey.server.mvc.Template;

import ru.yandex.autoschool.weather.services.WeatherService;
import ru.yandex.autoschool.weather.models.Weather;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * eroshenkoam
 * 29/10/14
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class IndexResource {

    @GET
    @Path("/")
    @Template(name = "/index.ftl")
    public Weather getIndex() {
        return getWeather(null, null, null);
    }

    @GET
    @Path("/{city}")
    @Template(name = "/index.ftl")
    public Weather getIndex(@PathParam("city") String city) {
        return getWeather(city, null, null);
    }

    @GET
    @Path("/{city}/{region}")
    @Template(name = "/index.ftl")
    public Weather getIndex(@PathParam("city") String city, @PathParam("region") String region) {
        return getWeather(city, region, null);
    }
    
    @GET
    @Path("/{city}/{region}/{scale}")
    @Template(name = "/index.ftl")
    public Weather getIndex(@PathParam("city") String city, @PathParam("region") String region,
    											@PathParam("scale") String scale) {
        return getWeather(city, region, scale);
    }

    private static Weather getWeather(String city, String region, String scale) {
        WeatherService weatherService = new WeatherService();
        return weatherService.getWeather(city, region, scale);
    }
}