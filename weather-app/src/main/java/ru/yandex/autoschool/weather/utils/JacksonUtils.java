package ru.yandex.autoschool.weather.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Artem Eroshenko <erosenkoam@me.com>
 */
public class JacksonUtils {

    private JacksonUtils() {
    }

    public static <T> T fromJson(String content, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(content, clazz);
        } catch (IOException e) {
            return null;
        }
    }
}
