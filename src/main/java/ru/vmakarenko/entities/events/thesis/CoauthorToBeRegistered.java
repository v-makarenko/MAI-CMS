package ru.vmakarenko.entities.events.thesis;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Inheritance
@DiscriminatorValue("tbr")
public class CoauthorToBeRegistered extends Coauthor {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

 
