package ru.vmakarenko.services;

import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.dto.users.UserSignUpDto;
import ru.vmakarenko.entities.users.User;
import ru.vmakarenko.util.Util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class UserService {
    @Inject
    private UserDao userDao;

    @Inject
    private MapperService mapperService;

    public UserDto getByEmailAndPassword(String email, String password) {
        return mapperService.map(userDao.getByEmailAndPassword(email, password), UserDto.class);
    }

    public RestResponse create(UserSignUpDto user){
        if(!user.getPassword().equals(user.getPassword2())){
            return RestResponse.createError(RestResponse.ErrorCodes.PASSWORD_NOT_MATCH);
        } else {
            userDao.insert(mapperService.map(user, User.class));
            return RestResponse.createOk();
        }
    }

    public UserDto getCurrentUser(HttpServletRequest request) {
        return mapperService.map(
                userDao.getByEmail(Util.getCookieValueFromRequest(AccessAuthDto.PARAM_AUTH_EMAIL, request))
                , UserDto.class);
    }

    public void update(UserDto userDto){
        userDao.update(mapperService.map(userDto, User.class));
    }

    public Object getAll() {
        return mapperService.map(userDao.findAll(), UserDto.class);
    }
}
