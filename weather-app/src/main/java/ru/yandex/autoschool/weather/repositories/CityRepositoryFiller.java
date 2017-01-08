package ru.yandex.autoschool.weather.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.yandex.autoschool.weather.entity.City;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import static ru.yandex.autoschool.weather.rest.resources.IndexResource.CITIES_FILE_PATH;

/**
 * @author lanwen (Merkushev Kirill)
 */
@Component
@Slf4j
public class CityRepositoryFiller implements ApplicationListener<ContextRefreshedEvent> {
    @Inject
    private CityRepository cityRepository;

    @Inject
    private ObjectMapper mapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.warn("Start DB initialization...");

        String path = getClass().getClassLoader().getResource(CITIES_FILE_PATH).getFile();
        try (Stream<String> lines = lines(Paths.get(path))) {
            long newCities = lines
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
                    .map(city -> cityRepository.save(city))
                    .count();

            log.info("DB initialization finished with {} new cities", newCities);

        } catch (IOException e) {
            throw new IllegalStateException("Cant read suggests file", e);
        }
    }
}
