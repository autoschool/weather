package ru.yandex.autoschool.weather.web;

import org.junit.Test;

/**
 * eroshenkoam
 * 29/10/14
 */
public class WeatherTest {

//    private WebDriver driver = new PhantomJSDriver();

    private String baseUrl = "http://localhost:8080";
    private String invalidCityUrl = baseUrl + "/thereisnosuchvity";
    private String invalidCityCaption = "Invalid city name";

//    @Before
//    public void openHomePage() {
//        driver.get(baseUrl);
//    }

    @Test
    public void cityByDefaultTest() {
//        String city = driver.findElement(By.className("city")).getText();
//        assertThat(city, notNullValue());
//        assertThat(city, equalTo(OpenWeatherService.DEFAULT_CITY));
    }

    @Test
    public void temperatureByDefaultInCelsius() {
//        String temperature = driver.findElement(By.className("temperature")).getText();
//        assertThat(temperature, notNullValue());
//        assertThat(temperature, endsWith("°C"));
    }

    @Test
    public void invalidCityTest() {
//        driver.get(invalidCityUrl);
//        String city = driver.findElement(By.className("city")).getText();
//        assertThat(city, notNullValue());
//        assertThat(city, equalTo(invalidCityCaption));
//        String temperature = driver.findElement(By.className("temperature")).getText();
//        assertThat(temperature, notNullValue());
//        assertThat(temperature, equalTo("???"));
//        driver.get(baseUrl);
    }
}
