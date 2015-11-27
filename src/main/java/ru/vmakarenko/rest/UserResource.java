package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.common.TokenService;
import ru.vmakarenko.dao.filters.UserFilter;
import ru.vmakarenko.dto.users.*;
import ru.vmakarenko.services.AuthService;
import ru.vmakarenko.services.UserService;
import ru.vmakarenko.util.Util;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.UUID;

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
    public Response getCurrent(@Context HttpServletRequest request) {
        return Response
                .ok(RestResponse.createOk().data(userService.getCurrentUser(request)))
                .build();
    }

    @Path("{id}")
    @GET
    public Response getCurrent(@PathParam("id") UUID id) {
        return Response
                .ok(RestResponse.createOk().data(userService.find(id)))
                .build();
    }

    @Path("getAll")
    @GET
    public Response getAll() {
        return Response
                .ok(RestResponse.createOk().data(userService.getAll()))
                .build();
    }

    @Path("getFiltered")
    @POST
    public Response getFiltered(UserFilter filter) {
        return Response
                .ok(RestResponse.createOk().data(userService.getFiltered(filter)))
                .build();
    }

    @Path("createUser")
    @POST
    public Response createUser(UserDto userDto) {
        userService.create(userDto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @PUT
    public Response update(UserDto userDto) {
        userService.update(userDto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @Path("checkEmail")
    @GET
    public Response checkEmail(String email) {
        return Response
                .ok(RestResponse.createOk().data(userService.checkEmail(email)))
                .build();
    }

    @Path("changePassword")
    @POST
    public Response changePassword(ChangePasswordDto dto) {
        userService.changePassword(dto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @GET
    @Path("confRegistered")
    public Response getAllConfRegistered(@QueryParam("eventId") UUID eventId) {
       return Response.ok(RestResponse.createOk().data(userService.getAllConfRegistered(eventId))).build();
    }
}
