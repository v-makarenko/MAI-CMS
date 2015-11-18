package ru.vmakarenko.entities.events;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.*;

/**
 * Created by VMakarenko on 13.11.2015.
 */
@Entity
@Table(name="sections")
public class Section extends DomainEntity {
    @Column(name="name")
    private String name;
    @Column(name="letter")
    private String letter;
    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
