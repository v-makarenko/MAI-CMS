package ru.vmakarenko.dao.common;

public enum FilterType {
    LESS("<"), MORE(">"), LIKE("like"), EQUALS("="), NULLABLE("is");

    private final String name;

    private FilterType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        return name;
    }
    }