package ru.yandex.autoschool.weather.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiOperation;
import ru.yandex.autoschool.weather.entity.City;
import ru.yandex.autoschool.weather.models.Weather;
import ru.yandex.autoschool.weather.repositories.CityRepository;
import ru.yandex.autoschool.weather.services.OpenWeatherService;
import ru.yandex.autoschool.weather.services.WeatherService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.length;
import static ru.yandex.autoschool.weather.utils.JacksonUtils.fromJson;

/**
 * index resource
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class IndexResource {

    @Inject
    private WeatherService weather;

    @Inject
    private CityRepository cityRepository;

    /**
     * Returns actual weather for city
     *
     * @param city   city name
     * @param region region (ru, us, fr...)
     * @return weather object
     */
    @GET
    @Path("/weather")
    @ApiOperation(value = "", response = Weather.class)
    public Weather getIndex(@NotNull @QueryParam("city") String city,
                            @DefaultValue(OpenWeatherService.DEFAULT_REGION)
                            @QueryParam("region") String region) {
        return weather.getWeather(city, region);
    }

    /**
     * Init of all cities
     *
     * @return cities list
     */
    @GET
    @Path("/init")
    @SuppressWarnings("unchecked")
    public List<City> init() {
        if (cityRepository.count() != 0) {
            return Collections.emptyList();
        }

        String path = getClass().getClassLoader().getResource("data/cities.json").getFile();
        try (Stream<String> lines = lines(Paths.get(path))) {
            List<City> cities = lines.map(line -> fromJson(line, City.class))
                    .filter(city -> city != null)
                    .collect(Collectors.toList());
            return cityRepository.save(cities);
        } catch (IOException e) {
            throw new RuntimeException("Cant read suggests file", e);
        }
    }


    /**
     * Suggest for city
     *
     * @param query query to search (more than 2 symb)
     * @return cities list
     */
    @GET
    @Path("/suggest")
    public List<City> suggest(@QueryParam("query") String query) {
        if (isBlank(query) || length(query) < 2) {
            return Collections.emptyList();
        }
        return cityRepository.findByNameContainingIgnoreCase(query);
    }

    /**
     * List of all cities
     *
     * @param limit integer value
     * @return cities list
     */
    @GET
    @Path("/cities")
    public String cities(@DefaultValue("0") @QueryParam("limit") Integer limit) {
        String path = getClass().getClassLoader().getResource("data/suggest.json").getFile();

        try (Stream<String> lines = lines(Paths.get(path))) {
            return lines
                    .limit(limit > 0 ? limit : Integer.MAX_VALUE)
                    .collect(toList()).toString();
        } catch (IOException e) {
            throw new RuntimeException("Cant read suggests file", e);
        }
    }
}