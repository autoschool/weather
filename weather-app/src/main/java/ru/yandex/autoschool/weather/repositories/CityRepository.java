package ru.yandex.autoschool.weather.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.autoschool.weather.entity.City;

import java.util.List;

/**
 * @author Artem Eroshenko <erosenkoam@me.com>
 */
@Transactional
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByNameContainingIgnoreCase(String query);
}
