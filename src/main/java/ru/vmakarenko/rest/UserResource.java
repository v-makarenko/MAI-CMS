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
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/user")
@Consumes("application/json")
@Produces("application/json")
public class UserResource {
    @Inject
    private UserService userService;

    @Path("getCurrent")
    @GET
    public Response getCurrent(@Context HttpServletRequest request){
        return Response
                .ok(RestResponse.createOk().data(userService.getCurrentUser(request)))
                .build();
    }

    @Path("getAll")
    @GET
    public Response getAll(){
        return Response
                .ok(RestResponse.createOk().data(userService.getAll()))
                .build();
    }

    @Path("createUser")
    @POST
    public Response createUser(UserDto userDto){
        userService.create(userDto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @PUT
    public Response update(UserDto userDto){
        userService.update(userDto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @Path("checkEmail")
    @GET
    public Response checkEmail(String email){
        return Response
                .ok(RestResponse.createOk().data(userService.checkEmail(email)))
                .build();
    }
}
