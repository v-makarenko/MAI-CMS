package ru.vmakarenko.entities.users;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="universities")
public class WorkingPlace extends DomainEntity {
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "long_name")
    private String name;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String longName) {
        this.name = longName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
