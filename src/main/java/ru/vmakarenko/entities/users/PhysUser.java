package ru.vmakarenko.entities.users;

import ru.vmakarenko.entities.DomainEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Entity
@DiscriminatorValue("u")
public class PhysUser extends AbstractUser {
    @Column(name="surname")
    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }}
