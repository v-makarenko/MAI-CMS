package ru.vmakarenko.entities.events;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.messages.EmailTemplate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="user_def_events")
public class UserDefinedEvent extends DomainEntity {
    @Column(name="name")
    private String name;
    @Column(name="is_broadcast")
    private boolean broadcast;
    @Column(name="description")
    private String description;
    @ManyToOne
    @JoinColumn(name="template_id")
    private EmailTemplate template;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EmailTemplate getTemplate() {
        return template;
    }

    public void setTemplate(EmailTemplate template) {
        this.template = template;
    }
}
