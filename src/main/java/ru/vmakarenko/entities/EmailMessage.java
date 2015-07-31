package ru.vmakarenko.entities;

import javax.persistence.*;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name = "email_messages")
public class EmailMessage extends DomainEntity{
    @Column(name = "TO_ADDR")
    private String to;
    @Column(name = "IS_SENT")
    private Boolean sent;
    @Column(name = "MSG_TEXT")
    private String text;
    // TODO rename in db
    @Column(name = "MSG_TITLE")
    private String subject;


    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
}
