package ru.vmakarenko.entities.users;

import org.dom4j.tree.AbstractEntity;
import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="universities")
public class University extends DomainEntity {
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "long_name")
    private String longName;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }
}
