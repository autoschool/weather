package ru.yandex.autoschool.weather;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * eroshenkoam
 * 29/10/14
 */
public class Server extends ResourceConfig {
    public Server() {
        packages(Server.class.getPackage().getName());
    }
}