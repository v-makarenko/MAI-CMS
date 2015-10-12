package ru.vmakarenko.services;

import org.apache.commons.lang.RandomStringUtils;
import ru.vmakarenko.dao.EventsDao;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.entities.events.Event;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class PasswordService {
    public String generatePassword(){
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
