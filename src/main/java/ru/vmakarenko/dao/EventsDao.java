package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.messages.InnerMessage;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class EventsDao extends GenericDao<Event> {

}
