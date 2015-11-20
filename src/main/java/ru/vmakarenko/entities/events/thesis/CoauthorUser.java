package ru.vmakarenko.entities.events.thesis;

import ru.vmakarenko.entities.users.User;

import javax.persistence.*;

/**
 * Created by VMakarenko on 7/15/2015.
 */
@Entity
@Inheritance
@DiscriminatorValue("reg")
public class CoauthorUser extends Coauthor {
    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

 
