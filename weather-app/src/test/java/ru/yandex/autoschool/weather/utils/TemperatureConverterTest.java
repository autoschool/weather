package ru.yandex.autoschool.weather.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static ru.yandex.autoschool.weather.utils.TemperatureUnit.CELSIUS;
import static ru.yandex.autoschool.weather.utils.TemperatureUnit.FAHRENHEIT;
import static ru.yandex.autoschool.weather.utils.TemperatureUnit.KELVIN;


/**
 * eroshenkoam
 * 29/10/14
 */
@RunWith(Parameterized.class)
public class TemperatureConverterTest {

    private double celsius;
    private double kelvin;
    private double fahrenheit;

    public TemperatureConverterTest(double celsius, double kelvin, double fahrenheit) {
        this.kelvin = kelvin;
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestInput() {
        return Arrays.asList(
                new Object[]{2, 275.15, 35.6},
                new Object[]{0, 273.15, 32}
        );
    }

    @Test
    public void celsiusToKelvinConverterTest() {
        assertThat(kelvin, equalTo(KELVIN.fromCelsius(celsius)));
    }

    @Test
    public void kelvinToCelsiusConverterTest() {
        assertThat(celsius, equalTo(CELSIUS.fromKelvin(kelvin)));
    }

    @Test
    public void kelvinTofahrenheitConverterTest() {
        assertThat(fahrenheit, equalTo(FAHRENHEIT.fromKelvin(kelvin)));
    }

    @Test
    public void fahrenheitToKelvinConverterTest() {
        assertThat(kelvin, equalTo(KELVIN.fromFahrenheit(fahrenheit)));
    }
}
