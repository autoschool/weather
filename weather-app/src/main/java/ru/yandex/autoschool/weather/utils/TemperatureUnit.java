package ru.yandex.autoschool.weather.utils;

import lombok.AllArgsConstructor;

/**
 * @author lanwen (Merkushev Kirill)
 */
@AllArgsConstructor
public enum TemperatureUnit {

    KELVIN("°K") {

        @Override
        public double fromCelsius(double value) {
            return value + 273.15;
        }

        @Override
        public double fromFahrenheit(double value) {
            return (value - 32)*5/9 + 273.15;
        }
    },

    CELSIUS("°C") {
        @Override
        public double fromKelvin(double value) {
            return value - 273.15;
        }

        @Override
        public double fromFahrenheit(double value) {
            return fromKelvin(KELVIN.fromFahrenheit(value));
        }
    },

    FAHRENHEIT("°F") {
        @Override
        public double fromKelvin(double value) {
            return 1.8*(value - 273.15) + 32;
        }

        @Override
        public double fromCelsius(double value) {
            return fromKelvin(KELVIN.fromCelsius(value));
        }
    };

    public double from(double value, TemperatureUnit unit) {
        switch (unit) {
            case CELSIUS: return fromCelsius(value);
            case KELVIN: return fromKelvin(value);
            case FAHRENHEIT: return fromFahrenheit(value);

            default: throw new AbstractMethodError();
        }
    }

    private String unit;

    public double fromKelvin(double value) {
        return value;
    }

    public double fromCelsius(double value) {
        return value;
    }

    public double fromFahrenheit(double value) {
        return value;
    }

    @Override
    public String toString() {
        return unit;
    }
}
