package ru.vmakarenko.entities.messages;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.users.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name = "inner_messages")
public class InnerMessage extends DomainEntity {
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User from;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User to;
    @Column(name = "text")
    private String text;
    @Column(name="message_time")
    private Long time;

//    private List<InnerMessageAttaches>
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @PrePersist
    @PreUpdate
    public void preUpdate(){
        if(time == null){
            time = Calendar.getInstance().getTime().getTime();
        }
    }
}
