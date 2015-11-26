package ru.vmakarenko.dto.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vmakarenko.dto.common.SectionDto;
import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.events.Section;
import ru.vmakarenko.entities.events.thesis.Coauthor;
import ru.vmakarenko.entities.users.User;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThesisDto extends DomainEntity {
    private String name;
    private UUID sectionId;
    private String sectionName;
    private List<CoauthorDto> coauthorsList;
    private UUID eventId;
    private UUID userId;
    private UUID fileId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CoauthorDto> getCoauthorsList() {
        return coauthorsList;
    }

    public void setCoauthorsList(List<CoauthorDto> coauthorsList) {
        this.coauthorsList = coauthorsList;
    }


    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public UUID getSectionId() {
        return sectionId;
    }

    public void setSectionId(UUID sectionId) {
        this.sectionId = sectionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getFileId() {
        return fileId;
    }

    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }
}


