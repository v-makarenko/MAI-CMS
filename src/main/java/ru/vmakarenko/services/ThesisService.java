package ru.vmakarenko.services;

import ru.vmakarenko.dao.*;
import ru.vmakarenko.dto.common.WorkingPlaceDto;
import ru.vmakarenko.dto.events.ThesisDto;
import ru.vmakarenko.entities.events.thesis.Coauthor;
import ru.vmakarenko.entities.events.thesis.Thesis;
import ru.vmakarenko.entities.users.WorkingPlace;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class ThesisService {
    @Inject
    private ThesisDao dao;
    @Inject
    private MapperService mapperService;
    @Inject
    private SectionDao sectionDao;
    @Inject
    private EventsDao eventDao;
    @Inject
    private UserDao userDao;

    private Thesis fillThesis(ThesisDto dto){
        Thesis thesis = dao.update(mapperService.map(dto, Thesis.class));
        thesis.setSection(sectionDao.find(dto.getSectionId()));
        thesis.setEvent(eventDao.find(dto.getEventId()));
        thesis.setUser(userDao.find(dto.getUserId()));
        return thesis;
    }


    public void create(ThesisDto dto) {
        dao.insert(fillThesis(dto));
    }

    public void update(ThesisDto dto) {
        dao.update(fillThesis(dto));
    }


    public List<ThesisDto> getConfirmed(UUID eventId, UUID userId) {
        return mapperService.map(dao.getByEventAndUser(eventId, userId)
                .stream()
                .filter(item -> item.getCoauthorsList()
                        .stream()
                        .allMatch(Coauthor::isConfirmed))
                .collect(Collectors.toList()), ThesisDto.class);
    }

    public List<ThesisDto> getWaitingConfirmationFromYou(UUID eventId, UUID userId) {
        return mapperService.map(dao.getByEventAndUser(eventId, userId)
                .stream()
                .filter(item -> item.getCoauthorsList()
                        .stream()
                        .anyMatch(ca -> !ca.isConfirmed()))
                .collect(Collectors.toList()), ThesisDto.class);
    }

    public List<ThesisDto> getWaitingConfirmationFromCoauthors(UUID eventId, UUID userId) {
        return mapperService.map(dao.getByEventAndUser(eventId, userId)
                .stream()
                .filter(item -> false)
                .collect(Collectors.toList()), ThesisDto.class);
    }

    public void delete(UUID id) {
        dao.trueDelete(id);
    }
}