package ru.vmakarenko.services;

import ru.vmakarenko.dao.*;
import ru.vmakarenko.dto.events.ThesisDto;
import ru.vmakarenko.entities.events.thesis.Coauthor;
import ru.vmakarenko.entities.events.thesis.Thesis;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class CoauthorService {
    @Inject
    private CoauthorDao coauthorDao;

    public void delete(UUID id) {
        coauthorDao.trueDelete(id);
    }
}