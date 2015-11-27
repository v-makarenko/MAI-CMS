package ru.vmakarenko.dto.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.events.Section;
import ru.vmakarenko.entities.events.thesis.Coauthor;
import ru.vmakarenko.entities.users.User;

import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 7/15/2015.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoauthorDto extends DomainEntity {
    private String name;
    private String surname;
    private String patronymic;
    private UUID userId;
    private String email;
    private UUID thesisId;
    private String dtype;
    private String snpLong;
    private String snpShort;
    private boolean confirmed;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getSnpLong() {
        return snpLong;
    }

    public void setSnpLong(String snpLong) {
        this.snpLong = snpLong;
    }

    public String getSnpShort() {
        return snpShort;
    }

    public void setSnpShort(String snpShort) {
        this.snpShort = snpShort;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getThesisId() {
        return thesisId;
    }

    public void setThesisId(UUID thesisId) {
        this.thesisId = thesisId;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}


