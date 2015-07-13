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
@DiscriminatorValue("r")
public class RestUser extends AbstractUser {
    @Column(name="contact_name")
    private String contactName;


    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
