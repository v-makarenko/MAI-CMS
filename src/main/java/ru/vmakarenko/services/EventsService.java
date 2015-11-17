package ru.vmakarenko.services;

import ru.vmakarenko.dao.EventsDao;
import ru.vmakarenko.dao.MessageDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.dto.events.PresenceDto;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.messages.InnerMessage;
import ru.vmakarenko.entities.users.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.swing.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class EventsService {
    @Inject
    private EventsDao dao;
    @Inject
    private UserDao usersDao;
    @Inject
    private MapperService mapperService;
    @Inject
    private CurrentService currentService;

    public EventDto get(UUID id) {
        return mapperService.map(dao.find(id), EventDto.class);
    }

    public void save(EventDto dto) {
        dao.update(mapperService.map(dto, Event.class));
    }

    public void delete(UUID id) {
        dao.trueDelete(id);
    }

    public List<EventDto> getAll() {
        List<Event> eventList = dao.findAll();
        List<EventDto> dtoList = mapperService.map(eventList, EventDto.class);
        User user = usersDao.getByEmail(currentService.getEmail());

        dtoList.forEach(eventDto -> {
            Event event = eventList.get(eventList.indexOf(new Event().id(eventDto.getId())));
            eventDto.setPresent(event.isParticipating(user.getId()));
        });
        return dtoList;
    }

    public void setPresence(PresenceDto dto) {
        User user = usersDao.find(dto.getUserId());
        Event event = dao.find(dto.getEventId());
        if (dto.isPresent()) {
            event.getUserList().add(user);
        } else if (event.getUserList().contains(user)) {
            event.getUserList().remove(user);
        }
        dao.update(event);
    }
}
