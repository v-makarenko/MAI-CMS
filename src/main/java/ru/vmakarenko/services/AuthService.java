package ru.vmakarenko.services;

import ru.vmakarenko.dao.UserDao;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.UserAuthDto;
import ru.vmakarenko.entities.users.AbstractUser;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/26/2015.
 */
@Stateless
public class AuthService {
    @Inject
    UserService userService;

    public AccessAuthDto login(UserAuthDto dto){
        AbstractUser user = userService.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user != null) {
            AccessAuthDto accessAuthDto = new AccessAuthDto();
            accessAuthDto.setId(dto.getUsername());
            accessAuthDto.setToken(UUID.randomUUID().toString());
            return accessAuthDto;
        }
        return null;
    }

}
