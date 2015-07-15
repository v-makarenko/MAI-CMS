package ru.vmakarenko.services;

import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.dto.users.UserSignUpDto;
import ru.vmakarenko.entities.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class UserService {
    @Inject
    UserDao userDao;

    @Inject
    MapperService mapperService;

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

}
