package ru.vmakarenko.dao;

import ru.vmakarenko.dao.generic.GenericDao;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.messages.InnerMessage;

import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class EventsDao extends GenericDao<Event> {

    public List<Event> getNext() {
        return em.createQuery("Select e FROM Event e where e.startDate > :today order by e.startDate asc", Event.class)
                .setParameter("today", Calendar.getInstance().getTime())
                .getResultList();
    }

    public List<Event> getPrevious() {
        return em.createQuery("Select e FROM Event e where e.startDate < :today order by e.startDate desc", Event.class)
                .setParameter("today", Calendar.getInstance().getTime())
                .getResultList();
    }

    public Event getCurrent() {
        List<Event> eventList =  em.createQuery("Select e FROM Event e where e.startDate < :today and e.endDate > :today", Event.class)
                .setParameter("today", Calendar.getInstance().getTime())
                .getResultList();
        return eventList.size() > 0 ? eventList.get(0) : null;
    }
}
