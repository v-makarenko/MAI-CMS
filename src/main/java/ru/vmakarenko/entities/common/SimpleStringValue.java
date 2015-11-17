package ru.vmakarenko.entities.common;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by VMakarenko on 13.11.2015.
 */
@Entity
@Table(name="s_string_values")
public class SimpleStringValue extends DomainEntity {
    @Column(name ="value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
