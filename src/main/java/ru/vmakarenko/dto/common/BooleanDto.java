package ru.vmakarenko.dto.common;

/**
 * Created by VMakarenko on 4/27/2015.
 */
public class BooleanDto extends AbstractDto {
    private Boolean value;

    public BooleanDto(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
