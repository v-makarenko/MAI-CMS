package ru.vmakarenko.dto.events.financial;

import ru.vmakarenko.entities.FakeDeleteDomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public class FinancialDocumentTypeDto extends FakeDeleteDomainEntity {
    private String name;
    private UUID eventId;
    private String exampleFileName;
    private UUID exampleFileId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getExampleFileId() {
        return exampleFileId;
    }

    public void setExampleFileId(UUID exampleFileId) {
        this.exampleFileId = exampleFileId;
    }

    public String getExampleFileName() {
        return exampleFileName;
    }

    public void setExampleFileName(String exampleFileName) {
        this.exampleFileName = exampleFileName;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}
