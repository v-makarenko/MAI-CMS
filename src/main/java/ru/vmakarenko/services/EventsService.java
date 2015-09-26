package ru.vmakarenko.services;

import ru.vmakarenko.dao.EventsDao;
import ru.vmakarenko.dao.MessageDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.messages.InnerMessage;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class EventsService {
    @Inject
    private EventsDao dao;
    @Inject
    private MapperService mapperService;

    public EventDto get(UUID id) {
        return mapperService.map(dao.find(id), EventDto.class);
    }

    public void save(EventDto dto){
        dao.update(mapperService.map(dto, Event.class));
    }

    public void delete(UUID id) {
        dao.trueDelete(id);
    }

    public List<EventDto> getAll() {
        return mapperService.map(dao.findAll(), EventDto.class);
    }
}
