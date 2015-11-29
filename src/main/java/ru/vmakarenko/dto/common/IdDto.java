package ru.vmakarenko.dto.common;

import java.util.UUID;

/**
 * Created by VMakarenko on 29.11.2015.
 */
public class IdDto extends AbstractDto {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
