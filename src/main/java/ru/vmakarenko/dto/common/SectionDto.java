package ru.vmakarenko.dto.common;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.Column;
import java.util.UUID;

/**
 * Created by VMakarenko on 17.11.2015.
 */
public class SectionDto extends DomainEntity {
    private String name;
    private String letter;
    private UUID eventId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}
