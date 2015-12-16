package ru.vmakarenko.entities.events.financial;

import ru.vmakarenko.entities.FakeDeleteDomainEntity;
import ru.vmakarenko.entities.common.SimpleStringValue;
import ru.vmakarenko.entities.common.files.FileEntry;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.users.User;
import ru.vmakarenko.entities.users.WorkingPlace;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="financial_documents_types")
public class FinancialDocumentType extends FakeDeleteDomainEntity {
    @Column(name = "name")
    private String name;
    @OneToOne
    @JoinColumn(name = "example_file_id")
    private FileEntry example;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileEntry getExample() {
        return example;
    }

    public void setExample(FileEntry example) {
        this.example = example;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
