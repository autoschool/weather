package ru.yandex.autoschool.weather.utils;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.internal.util.StringHelper;
import org.springframework.stereotype.Component;

/**
 * @author Artem Eroshenko <erosenkoam@me.com>
 */
@Component
public class NamingStrategy extends ImprovedNamingStrategy {

    public String classToTableName(String className) {
        return addUnderscores(StringHelper.unqualify(className)).toLowerCase();
    }

    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName,
                                       String propertyTableName, String referencedColumnName) {
        return super.foreignKeyColumnName(propertyName, propertyEntityName,
                propertyTableName, referencedColumnName) + "_" + referencedColumnName;
    }
}
