package ru.vmakarenko.services;

import ru.vmakarenko.dao.*;
import ru.vmakarenko.dto.common.WorkingPlaceDto;
import ru.vmakarenko.dto.events.ThesisDto;
import ru.vmakarenko.entities.events.thesis.*;
import ru.vmakarenko.entities.users.User;
import ru.vmakarenko.entities.users.WorkingPlace;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
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
    @Inject
    private CurrentService currentService;

    private Thesis fillThesis(ThesisDto dto) {
        Thesis thesis = new Thesis();
        thesis.setId(dto.getId());
        thesis.setSection(sectionDao.find(dto.getSectionId()));
        thesis.setEvent(eventDao.find(dto.getEventId()));
        thesis.setUser(userDao.find(dto.getUserId()));
        thesis.setName(dto.getName());
        if (dto.getCoauthorsList() != null && dto.getCoauthorsList().size() > 0) {
            thesis.setCoauthorsList(dto.getCoauthorsList().stream().map(item -> {
                        switch (item.getDtype()) {
                            case "reg":
                                CoauthorUser ca1 = new CoauthorUser();
                                ca1.setUser(userDao.find(item.getUserId()));
                                ca1.setThesis(thesis);
                                return ca1;
                            case "snp":
                                CoauthorSNP ca = mapperService.map(item, CoauthorSNP.class);
                                ca.setThesis(thesis);
                                ca.setName(item.getName());
                                ca.setSurname(item.getSurname());
                                ca.setPatronymic(item.getPatronymic());
                                return ca;
                            case "tbr":
                                CoauthorToBeRegistered ca3 = new CoauthorToBeRegistered();
                                // find if already registered
                                User possibleUser = userDao.getByEmail(item.getEmail());
                                if (possibleUser != null) {
                                    item.setDtype("reg");
                                    CoauthorUser caUser = new CoauthorUser();
                                    caUser.setUser(possibleUser);
                                    caUser.setThesis(thesis);
                                    return caUser;
                                } else {
                                    // really tbr coauthor
                                    ca3.setEmail(item.getEmail());
                                }
                                ca3.setThesis(thesis);
                                return ca3;
                        }
                        return new Coauthor();
                    }
            ).collect(Collectors.toList()));
        }
        if (thesis.getCoauthorsList().stream().noneMatch(item -> item instanceof CoauthorUser && ((CoauthorUser) item)
                .getUser().getEmail().equals(currentService.getEmail()))) {
            CoauthorUser coauthor = new CoauthorUser();
            coauthor.setConfirmed(true);
            coauthor.setThesis(thesis);
            coauthor.setUser(userDao.getByEmail(currentService.getEmail()));
            thesis.getCoauthorsList().add(coauthor);
        }


        return thesis;
    }


    public ThesisDto save(ThesisDto dto) {
        if (dto.getId() == null) {
            return mapperService.map(create(dto), ThesisDto.class);
        } else {
            return mapperService.map(update(dto), ThesisDto.class);
        }

    }

    public ThesisDto create(ThesisDto dto) {
        return mapperService.map(dao.insert(fillThesis(dto)), ThesisDto.class);
    }

    public ThesisDto update(ThesisDto dto) {
        return mapperService.map(dao.update(fillThesis(dto)), ThesisDto.class);
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

    public List<ThesisDto> getByEvent(UUID eventId, String status) {
        return mapperService.map(dao.getByEventAndUser(eventId, null)
                .stream()
                .collect(Collectors.toList()), ThesisDto.class);
    }

    public void delete(UUID id) {
        dao.trueDelete(id);
    }

    public ThesisDto find(UUID id) {
        return mapperService.map(dao.find(id), ThesisDto.class);
    }

}