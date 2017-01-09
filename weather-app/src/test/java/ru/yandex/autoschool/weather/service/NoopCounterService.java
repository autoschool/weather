package ru.yandex.autoschool.weather.service;

import org.springframework.boot.actuate.metrics.CounterService;

/**
 * @author lanwen (Merkushev Kirill)
 */
public class NoopCounterService implements CounterService {
    @Override
    public void increment(String metricName) {

    }

    @Override
    public void decrement(String metricName) {

    }

    @Override
    public void reset(String metricName) {

    }
}
