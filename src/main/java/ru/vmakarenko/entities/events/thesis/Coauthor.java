package ru.vmakarenko.entities.events.thesis;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.*;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="coauthors")
@Inheritance
@DiscriminatorColumn(name = "dtype")
public class Coauthor extends DomainEntity {
    @ManyToOne
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;
    @Column(name="confirmed")
    private boolean confirmed;
    @Column(name="dtype", insertable = false ,updatable = false)
    private String dtype;


    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }
}

 
