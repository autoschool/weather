package ru.yandex.autoschool.weather.rest.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.autoschool.weather.entity.City;
import ru.yandex.autoschool.weather.models.Weather;
import ru.yandex.autoschool.weather.repositories.CityRepository;
import ru.yandex.autoschool.weather.services.OpenWeatherService;
import ru.yandex.autoschool.weather.services.WeatherService;
import ru.yandex.autoschool.weather.utils.ResourceUtils;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.length;

/**
 * index resource
 */
@Path("/")
@Produces(ResourceUtils.APPLICATION_JSON_UTF8)
@Component
@Slf4j
public class IndexResource {

    public static final String CITIES_FILE_PATH = "data/cities.json";

    @Inject
    private WeatherService weather;

    @Inject
    private CityRepository cityRepository;

    @Inject
    private ObjectMapper mapper;

    /**
     * Returns actual weather for city
     *
     * @param city   city name
     * @param region region (ru, us, fr...)
     * @return weather object
     */
    @GET
    @Path("/weather")
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
        String path = getClass().getClassLoader().getResource(CITIES_FILE_PATH).getFile();
        try (Stream<String> lines = lines(Paths.get(path))) {
            return lines
                    .map(line -> {
                        try {
                            return mapper.readValue(line, City.class);
                        } catch (IOException e) {
                            log.debug("Can't read city from json", e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .filter(city -> cityRepository.findByName(city.getName()).isEmpty())
                    .map(city ->  cityRepository.save(city))
                    .collect(Collectors.toList());
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
        String path = getClass().getClassLoader().getResource(CITIES_FILE_PATH).getFile();

        try (Stream<String> lines = lines(Paths.get(path))) {
            return lines
                    .limit(limit > 0 ? limit : Integer.MAX_VALUE)
                    .collect(toList()).toString();
        } catch (IOException e) {
            throw new RuntimeException("Cant read suggests file", e);
        }
    }
}
