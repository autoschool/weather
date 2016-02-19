package ru.yandex.autoschool.weather;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

/**
 * eroshenkoam
 * 29/10/14
 */
public class Server extends ResourceConfig {
    public Server() {
        register(FreemarkerMvcFeature.class);
        packages(Server.class.getPackage().getName());
    }
}