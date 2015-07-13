package ru.vmakarenko.dao.anno;


import ru.vmakarenko.dao.common.FilterType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by User on 25.02.2015.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FilterParam {

    public FilterType filterType() default FilterType.EQUALS;
    public String joinPath() default "-";
    public String name() default "-";
}
