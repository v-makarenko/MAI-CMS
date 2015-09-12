package ru.vmakarenko.entities.users;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.Report;
import ru.vmakarenko.entities.users.User;

import javax.persistence.*;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="coauthors")
public class Coauthor extends DomainEntity {
    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
