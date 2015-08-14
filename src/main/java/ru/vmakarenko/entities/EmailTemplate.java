package ru.vmakarenko.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name = "email_templates")
public class EmailTemplate extends DomainEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "template_text")
    private String text;
    @Column(name = "email_topic")
    private String topic;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


}
