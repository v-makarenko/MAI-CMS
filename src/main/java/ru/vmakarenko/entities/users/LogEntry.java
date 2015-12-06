package ru.vmakarenko.entities.users;

import ru.vmakarenko.common.UserType;
import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="log_entries")
public class LogEntry extends DomainEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name="type")
    private String type;
    @Column(name="description")
    private String description;
    @Column(name="time")
    private Date time;
    @Column(name="success")
    private Boolean success;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
