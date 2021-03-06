package ru.vmakarenko.entities.events.thesis;

import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.common.files.FileEntry;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.events.Section;
import ru.vmakarenko.entities.users.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="thesises")
public class Thesis extends DomainEntity {
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    @OneToMany(mappedBy = "thesis", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Coauthor> coauthorsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private FileEntry fileEntry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coauthor> getCoauthorsList() {
        return coauthorsList;
    }

    public void setCoauthorsList(List<Coauthor> coauthorsList) {
        this.coauthorsList = coauthorsList;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


