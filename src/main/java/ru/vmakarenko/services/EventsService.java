package ru.vmakarenko.services;

import ru.vmakarenko.dao.EventsDao;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.dto.events.PresenceDto;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.entities.users.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
//@Transactional(Transactional.TxType.REQUIRES_NEW)
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

    public EventDto save(EventDto dto) {
        Event event = mapperService.map(dto, Event.class);
        event.setUserList(event.getUserList().stream().map(user -> usersDao.find(user.getId()))
                .collect(Collectors.toList()));
        return mapperService.map(dao.update(event), EventDto.class);
    }

    public void delete(UUID id) {
        dao.delete(id);
    }

    public List<EventDto> getAll() {
        List<Event> eventList = dao.findActive();
        eventList.stream().forEach(event -> event.getSectionList().size());
        eventList.stream().forEach(event -> event.getUserList().size());
        eventList.stream().forEach(event -> event.getFinancialDocumentTypeList().size());
        eventList.stream().forEach(event -> event.getOrgOrganisationList().size());
        eventList.stream().forEach(event -> event.getTechPeopleList().size());
        List<EventDto> dtoList = mapperService.map(eventList, EventDto.class);
        User user = usersDao.getByEmail(currentService.getEmail());
        dtoList.forEach(eventDto -> {
            Event event = eventList.get(eventList.indexOf(new Event().id(eventDto.getId())));
            eventDto.setPresent(event.isParticipating(user.getId()));
        });
        return dtoList;
    }

    public List<EventDto> getPreviousList(){
        List<Event> eventList = dao.getPrevious();
        eventList.stream().forEach(event -> event.getSectionList().size());
        return mapperService.map(eventList, EventDto.class);
    }
    public List<EventDto> getNextList(){
        List<Event> eventList = dao.getNext();
        eventList.stream().forEach(event -> event.getSectionList().size());
        return mapperService.map(eventList, EventDto.class);
    }


    public EventDto getCurrent() {
        return mapperService.map(dao.getCurrent(), EventDto.class);
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
