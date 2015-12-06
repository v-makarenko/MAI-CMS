package ru.vmakarenko.dto.events;

import ru.vmakarenko.dto.common.SectionDto;
import ru.vmakarenko.dto.common.WorkingPlaceDto;
import ru.vmakarenko.dto.users.UserNoPassDto;
import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.common.SimpleStringValue;
import ru.vmakarenko.entities.users.User;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public class LogEntryDto extends DomainEntity {
    private String userShortName;
    private String userLongName;
    private UUID userId;
    private String type;
    private String description;
    private Date time;
    private Boolean success;

    public String getUserShortName() {
        return userShortName;
    }

    public void setUserShortName(String userShortName) {
        this.userShortName = userShortName;
    }

    public String getUserLongName() {
        return userLongName;
    }

    public void setUserLongName(String userLongName) {
        this.userLongName = userLongName;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
