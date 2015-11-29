package ru.vmakarenko.services;

import ru.vmakarenko.dao.WorkingPlaceDao;
import ru.vmakarenko.dto.common.WorkingPlaceDto;
import ru.vmakarenko.dto.helper.WPConnectDto;
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
public class WPService {
    @Inject
    private WorkingPlaceDao workingPlaceDao;
    @Inject
    private MapperService mapperService;


    public void create(WorkingPlaceDto wpDto) {
        WorkingPlace wp = mapperService.map(wpDto, WorkingPlace.class);
        workingPlaceDao.insert(wp);
    }

    public void delete(UUID id) {
        workingPlaceDao.trueDelete(id);
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

    public void connect(WPConnectDto dto) {
        List<WorkingPlace> wpList = dto.getWpReduceList().stream().map(item -> workingPlaceDao.find(item.getId()))
                .collect(Collectors.toList());
        WorkingPlace originWP = workingPlaceDao.find(dto.getOriginId());
        wpList.forEach(item ->
                item.getEmployeeList().forEach(emp -> emp.setWorkingPlace(originWP)));
        wpList.forEach(wp -> {
            if (!wp.getId().equals(originWP.getId())) {
                workingPlaceDao.trueDelete(wp.getId());
            }
        });
        wpList.removeIf(workingPlace -> !workingPlace.getId().equals(originWP.getId()));
    }

}
