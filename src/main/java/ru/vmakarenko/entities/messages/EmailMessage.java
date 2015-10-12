package ru.vmakarenko.entities.messages;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.users.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name = "external_messages")
public class EmailMessage extends DomainEntity {
    @ManyToMany
    @JoinTable(name = "external_message_receivers",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> toList;
    @Column(name = "sent_status")
    private Boolean sentStatus = false;
    @Column(name = "send_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentTime;
    @Column(name = "TEXT")
    private String text;
    // TODO rename in db
    @Column(name = "topic")
    private String subject;


    public Boolean getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(Boolean sent) {
        this.sentStatus = sent;
    }

    public List<User> getToList() {
        return toList;
    }

    public void setToList(List<User> toList) {
        this.toList = toList;
    }

    public String getText() {
        return text;
    }

    public void setText(String msgText) {
        this.text = msgText;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }
}
