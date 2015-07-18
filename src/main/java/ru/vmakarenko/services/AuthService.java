package ru.vmakarenko.services;

import ru.vmakarenko.common.TokenService;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.UserAuthDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.util.Util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/26/2015.
 */
@Stateless
public class AuthService {
    @Inject
    private UserService userService;
    @Inject
    private TokenService tokenService;

    public boolean isAuthenticated(HttpServletRequest request){
        return tokenService.check(
                Util.getCookieValueFromRequest(AccessAuthDto.PARAM_AUTH_TOKEN, request),
                Util.getCookieValueFromRequest(AccessAuthDto.PARAM_AUTH_EMAIL, request)
                );
    }

    public AccessAuthDto login(UserAuthDto dto) {
        UserDto user = userService.getByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (user != null) {
            AccessAuthDto accessAuthDto = new AccessAuthDto();
            accessAuthDto.setEmail(dto.getEmail());
            accessAuthDto.setToken(UUID.randomUUID().toString());
            tokenService.add(accessAuthDto.getToken(), user);
            return accessAuthDto;
        }
        return null;
    }

}
