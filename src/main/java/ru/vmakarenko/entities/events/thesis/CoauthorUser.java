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
}

 
