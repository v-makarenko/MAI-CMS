package ru.vmakarenko.dto.common;

import ru.vmakarenko.entities.DomainEntity;

import java.util.UUID;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public class MessageDto extends DomainEntity{
    private UUID from;
    private String fromName;
    private UUID to;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getFrom() {
        return from;
    }

    public void setFrom(UUID from) {
        this.from = from;
    }

    public UUID getTo() {
        return to;
    }

    public void setTo(UUID to) {
        this.to = to;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
}
