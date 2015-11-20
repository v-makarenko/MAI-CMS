package ru.vmakarenko.dto.events;

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
public class CoauthorDto extends DomainEntity {
    private String name;
    private String surname;
    private String patronymic;
    private UUID userId;
    private String email;
    private UUID thesisId;

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
}


