package ru.vmakarenko.entities.events;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.messages.EmailTemplate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="user_def_events")
public class UserDefinedEvents extends DomainEntity {
    @Column(name="name")
    private String name;
    @Column(name="name")
    private boolean broadcast;
    @Column(name="name")
    private String description;
    @Column(name="name")
    private EmailTemplate template;
}
