package ru.vmakarenko.dto.common;

import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
public class CommonDto extends AbstractDto{
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
