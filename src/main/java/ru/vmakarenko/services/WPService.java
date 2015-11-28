package ru.vmakarenko.services;

import ru.vmakarenko.dao.WorkingPlaceDao;
import ru.vmakarenko.dto.common.WorkingPlaceDto;
import ru.vmakarenko.entities.users.WorkingPlace;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class WPService {
    @Inject
    private WorkingPlaceDao workingPlaceDao;
    @Inject
    private MapperService mapperService;


    public void create(WorkingPlaceDto wpDto) {
        WorkingPlace wp = mapperService.map(wpDto, WorkingPlace.class);
        workingPlaceDao.insert(wp);
    }

    public void update(WorkingPlaceDto wpDto) {
        workingPlaceDao.update(mapperService.map(wpDto, WorkingPlace.class));
    }

    public Object getAll() {
        return mapperService.map(workingPlaceDao.findAll(), WorkingPlaceDto.class);
    }

    public WorkingPlaceDto find(UUID id) {
        return mapperService.map(workingPlaceDao.find(id), WorkingPlaceDto.class);
    }
}
