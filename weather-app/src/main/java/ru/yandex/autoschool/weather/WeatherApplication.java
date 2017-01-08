package ru.yandex.autoschool.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import ru.yandex.autoschool.weather.clients.OpenWeatherClient;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

	@Bean
	@Profile("prod")
	public OpenWeatherClient openWeatherClient(@Value("${owm.token}") String token, ObjectMapper mapper) {
		return OpenWeatherClient.connect(token, mapper);
	}
}
