package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.common.TokenService;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.UserAuthDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.dto.users.UserSignUpDto;
import ru.vmakarenko.services.AuthService;
import ru.vmakarenko.services.UserService;
import ru.vmakarenko.util.Util;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/user")
public class UserResource {
    @Inject
    private UserService userService;

    @Path("getCurrent")
    @GET
    public Response getCurrentUser(@Context HttpServletRequest request){
        return Response
                .ok(RestResponse.createOk().data(userService.getCurrentUser(request)))
                .build();
    }

    @Path("update")
    @GET
    public Response update(UserDto userDto){
        userService.update(userDto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }
}
