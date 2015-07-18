package ru.vmakarenko.entities;

import javax.persistence.*;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name = "messages")
public class Message extends DomainEntity{
    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User from;
    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User to;
    @Column(name = "mgs")
    private String text;
    // maybe shuold add some attaches


    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
