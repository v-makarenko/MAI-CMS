package ru.vmakarenko.entities.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Table(name="working_places")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkingPlace extends DomainEntity {
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "long_name")
    private String name;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @OneToMany(mappedBy = "workingPlace")
    private List<User> employeeList;

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

    public List<User> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<User> employeeList) {
        this.employeeList = employeeList;
    }
}
