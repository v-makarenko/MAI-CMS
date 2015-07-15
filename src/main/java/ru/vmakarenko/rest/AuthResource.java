package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.common.TokenService;
import ru.vmakarenko.dto.common.CommonListResponse;
import ru.vmakarenko.dto.common.CommonResponse;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.UserAuthDto;
import ru.vmakarenko.dto.users.UserSignUpDto;
import ru.vmakarenko.services.AuthService;
import ru.vmakarenko.services.UserService;
import ru.vmakarenko.util.Util;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Calendar;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("auth")
@Consumes("application/json")
@Produces("application/json")
public class AuthResource {
    @Inject
    private UserService userService;
    @Inject
    private AuthService authService;
    @Inject
    private TokenService tokenService;

    @POST
    @Path("login")
    public Response login(@Context HttpServletRequest request, UserAuthDto userAuthDto) {
        AccessAuthDto responseDto = authService.login(userAuthDto);
        if (responseDto != null) {
            NewCookie cookie1 = new NewCookie(AccessAuthDto.PARAM_AUTH_EMAIL, responseDto.getToken());
            NewCookie cookie2 = new NewCookie(AccessAuthDto.PARAM_AUTH_TOKEN, responseDto.getEmail());
            return Response
                    .ok(responseDto)
                    .cookie(new NewCookie[]{cookie1, cookie2})
                    .build();
        } else {
            return Response.ok(RestResponse.createError(RestResponse.ErrorCodes.NOT_AUTHENTICATED)).build();
        }
    }

    @POST
    @Path("logout")
    public Response logout(@Context HttpServletRequest request) {
        tokenService.remove(Util.getCookieValueFromRequest(AccessAuthDto.PARAM_AUTH_TOKEN, request));
        return Response.ok(RestResponse.createOk()).build();
    }

    @GET
    @Path("isAuthenticated")
    public Response isAuthenticated(@Context HttpServletRequest request) {
        return Response.ok().build();
    }

    @GET
    @Path("getRoles")
    public Response getRoles() {
        return Response.ok().build();
    }

    @POST
    @Path("signUp")
    public Response signUp(UserSignUpDto dto) {
        return Response.ok(userService.create(dto)).build();
    }

}
