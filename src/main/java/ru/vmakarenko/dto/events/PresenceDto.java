package ru.vmakarenko.dto.events;

import java.util.UUID;

/**
 * Created by VMakarenko on 10/25/2015.
 */
public class PresenceDto {
    private  UUID userId;
    private UUID eventId;
    private boolean present;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
