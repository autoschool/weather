package ru.yandex.autoschool.weather.misc;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.yandex.autoschool.weather.services.OpenWeatherService;
import ru.yandex.autoschool.weather.services.StaticWeatherService;
import ru.yandex.autoschool.weather.services.WeatherService;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

/**
 * @author lanwen (Merkushev Kirill)
 */
@Provider
public class ServicesBinder implements Feature {
    @Override
    public boolean configure(FeatureContext context) {
        context.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(OpenWeatherService.class).to(WeatherService.class);
//                bind(StaticWeatherService.class).to(WeatherService.class);
            }
        });
        return true;
    }
}
