package ru.yandex.autoschool.weather.web;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import ru.yandex.autoschool.weather.models.WeatherMeasure;
import ru.yandex.autoschool.weather.services.WeatherService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;

/**
 * eroshenkoam
 * 29/10/14
 */
public class WeatherTest {

    private WebDriver driver = new PhantomJSDriver();

    private String baseUrl = "http://localhost:8080";
    private String invalidCityUrl = baseUrl + "/thereisnosuchvity";
    private String invalidCityCaption = "Invalid city name";

    @Before
    public void openHomePage() {
        driver.get(baseUrl);
    }

    @Test
    public void cityByDefaultTest() {
        String city = driver.findElement(By.className("city")).getText();
        assertThat(city, notNullValue());
        assertThat(city, equalTo(WeatherService.DEFAULT_CITY));
    }

    @Test
    public void temperatureByDefaultInCelsius() {
        String temperature = driver.findElement(By.className("temperature")).getText();
        assertThat(temperature, notNullValue());
        assertThat(temperature, endsWith(WeatherMeasure.CELSIUS.getAbbreviation()));
    }

    @Test
    public void invalidCityTest() {
        driver.get(invalidCityUrl);
        String city = driver.findElement(By.className("city")).getText();
        assertThat(city, notNullValue());
        assertThat(city, equalTo(invalidCityCaption));
        String temperature = driver.findElement(By.className("temperature")).getText();
        assertThat(temperature, notNullValue());
        assertThat(temperature, equalTo("???"));
        driver.get(baseUrl);
    }
}
