package ru.yandex.autoschool.weather.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static ru.yandex.autoschool.weather.utils.TemperatureFormatter.humanise;

/**
 * eroshenkoam
 * 29/10/14
 */
@RunWith(Parameterized.class)
public class TemperatureFormatterTest {

    private String humanisedTemperature;

    private double temperature;

    private String measure;

    public TemperatureFormatterTest(double temperature, String measure, String humanisedTemperature) {
        this.humanisedTemperature = humanisedTemperature;
        this.temperature = temperature;
        this.measure = measure;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestInput() {
        return Arrays.asList(
                new Object[]{2, "°K", "+2 °K"},
                new Object[]{0, "°C", "+0 °C"}
        );
    }

    @Test
    public void TemperatureHumanizerTest() {
        assertThat(humanisedTemperature, equalTo(humanise(temperature, measure)));
    }


}
