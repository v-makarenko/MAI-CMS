package ru.vmakarenko.entities.users;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.Report;
import ru.vmakarenko.entities.users.User;

import javax.persistence.*;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="external_authors")
public class ExternalAuthor extends DomainEntity {
    @Column(name= "name")
    private String name;
    @Column(name= "surnmae")
    private String surname;
    @Column(name= "patronymic")
    private String patronymic;
    @Column(name= "created_by_id")
    private User createdBy;
    @Column(name= "working_place")
    private String workingPlace;
    @Column(name= "position")
    private String position;
    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
