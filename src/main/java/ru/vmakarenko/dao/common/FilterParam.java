package ru.vmakarenko.dao.common;

/**
 * Created by User on 25.02.2015.
 */

/**
 * служебный класс для CommonFilter
 * @see ru.orgunit.filters.CommonFilter
 */
public class FilterParam {
    private FilterType filterType;          // тип фильтруемой величины
    private String fieldName;               // название фильтруемого поля
    private String paramName;               // название параметра для подстановки в Query  ( :%paramName% )
    private String joinName;                // alias для соотв. join'a
    private Object fieldValue;              // значение поля для подстановки

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getJoinName() {
        return joinName;
    }

    public void setJoinName(String joinName) {
        this.joinName = joinName;
    }
}
