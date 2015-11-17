package ru.vmakarenko.dto.common;

import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.entities.DomainEntity;
import ru.vmakarenko.entities.common.SimpleStringValue;
import ru.vmakarenko.entities.events.Section;
import ru.vmakarenko.entities.users.WorkingPlace;

import java.util.Date;
import java.util.List;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public class WorkingPlaceDto extends DomainEntity {
    private String name;
    private String shortName;
    private String country;
    private String city;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
