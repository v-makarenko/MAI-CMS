package ru.vmakarenko.dto.common;

/**
 * Created by VMakarenko on 4/27/2015.
 */
public class ObjectDto extends AbstractDto {
    private Object value;

    public ObjectDto(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
