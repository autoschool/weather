package ru.yandex.autoschool.weather.utils;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * @author Artem Eroshenko <erosenkoam@me.com>
 */
public class NamingStrategy extends ImprovedNamingStrategy {

    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName,
                                       String propertyTableName, String referencedColumnName) {
        return super.foreignKeyColumnName(propertyName, propertyEntityName,
                propertyTableName, referencedColumnName) + "_" + referencedColumnName;
    }
}
