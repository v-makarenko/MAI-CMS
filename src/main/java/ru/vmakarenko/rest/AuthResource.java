package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.common.TokenService;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.UserAuthDto;
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
@Path("auth")
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
            String path = "/";
            String domain = "";
            String comment = "";
            int maxAge = 60*60*24;
            boolean secure = false;
            NewCookie cookie1 = new NewCookie(AccessAuthDto.PARAM_AUTH_TOKEN, responseDto.getToken(), path, domain, comment, maxAge, secure);
            NewCookie cookie2 = new NewCookie(AccessAuthDto.PARAM_AUTH_EMAIL, responseDto.getEmail(), path, domain, comment, maxAge, secure);
            return Response
                    .ok(RestResponse.createOk().data(tokenService.get(responseDto.getToken())))
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
        if(authService.isAuthenticated(request)) {
            return Response.ok(RestResponse.createOk()).build();
        } else {
            return Response.ok(RestResponse
                    .createError(RestResponse.ErrorCodes.NOT_AUTHENTICATED))
                    .build();
        }
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
