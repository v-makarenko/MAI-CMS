package ru.vmakarenko.dto.findocs;

import java.util.UUID;

/**
 * Created by VMakarenko on 19.12.2015.
 */
public class CreateForUserDto {


    private UUID userId, eventId;

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
