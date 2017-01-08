package ru.yandex.autoschool.weather.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.ws.rs.core.MediaType;

/**
 * @author Artem Eroshenko <erosenkoam@me.com>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceUtils {
    public final static String APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON + ";charset=utf-8";
}
