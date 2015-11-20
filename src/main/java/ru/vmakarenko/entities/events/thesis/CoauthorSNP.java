package ru.vmakarenko.entities.events.thesis;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.*;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Inheritance
@DiscriminatorValue("snp")
public class CoauthorSNP extends Coauthor {
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @Column(name="patronymic")
    private String patronymic;

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
}

 
