package ru.vmakarenko.services;

import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dao.filters.CommonFilter;
import ru.vmakarenko.dao.filters.UserFilter;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.dto.users.UserSignUpDto;
import ru.vmakarenko.entities.users.AbstractUser;
import ru.vmakarenko.entities.users.RestUser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Stateless
public class UserService {
    @Inject
    UserDao userDao;

    @Inject
    MapperService mapperService;
    public void create(UserSignUpDto dto){
        userDao.insert(mapperService.map(dto, RestUser.class));
    }

    public AbstractUser findByUsernameAndPassword(String username, String password){
        List<AbstractUser> abstractUserList =  userDao.findAll(new CommonFilter(new UserFilter().username(username).password(password)));
        if(abstractUserList.size() > 0 ){
            return abstractUserList.get(0);
        }else{
            return null;
        }
    }
    public UserDto findByUsername(String username){
        List<AbstractUser> abstractUserList =  userDao.findAll(new CommonFilter(new UserFilter().username(username)));
        if(abstractUserList.size() > 0 ){
            AbstractUser user = abstractUserList.get(0);
            return mapperService.map(user, UserDto.class);
        }else{
            return null;
        }
    }

}
