package ru.vmakarenko.services;

import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dao.WPDao;
import ru.vmakarenko.dto.common.WorkingPlaceDto;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.ChangePasswordDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.entities.users.User;
import ru.vmakarenko.entities.users.WorkingPlace;
import ru.vmakarenko.enums.EmailCheckResult;
import ru.vmakarenko.exceptions.MaiWebappException;
import ru.vmakarenko.util.Util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class WPService {
    @Inject
    private WPDao wpDao;
    @Inject
    private MapperService mapperService;


    public void create(WorkingPlaceDto wpDto) {
        WorkingPlace wp = mapperService.map(wpDto, WorkingPlace.class);
        wpDao.insert(wp);
    }

    public void update(WorkingPlaceDto wpDto) {
        wpDao.update(mapperService.map(wpDto, WorkingPlace.class));
    }

    public Object getAll() {
        return mapperService.map(wpDao.findAll(), WorkingPlaceDto.class);
    }

    public WorkingPlaceDto find(UUID id) {
        return mapperService.map(wpDao.find(id), WorkingPlaceDto.class);
    }
}
