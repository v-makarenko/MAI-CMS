package ru.vmakarenko.rest;


import ru.vmakarenko.dto.common.CommonListResponse;
import ru.vmakarenko.dto.common.CommonResponse;
import ru.vmakarenko.dto.users.AccessAuthDto;
import ru.vmakarenko.dto.users.UserAuthDto;
import ru.vmakarenko.dto.users.UserSignUpDto;
import ru.vmakarenko.services.AuthService;
import ru.vmakarenko.services.UserService;

import javax.inject.Inject;
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
    UserService userService;
    @Inject
    AuthService authService;


    @POST
    @Path("login")
    public Response login(@Context HttpServletRequest request, UserAuthDto userAuthDto) {
        AccessAuthDto responseDto = authService.login(userAuthDto);
        if (responseDto != null) {
            request.getSession().setAttribute(AccessAuthDto.PARAM_AUTH_ID, responseDto.getId());
            request.getSession().setAttribute(AccessAuthDto.PARAM_AUTH_TOKEN, responseDto.getToken());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.HOUR, 1);
            NewCookie cookie1 = new NewCookie(AccessAuthDto.PARAM_AUTH_ID, responseDto.getToken());
            NewCookie cookie2 = new NewCookie(AccessAuthDto.PARAM_AUTH_TOKEN, responseDto.getId());
            return Response
                    .ok(responseDto)
                    .cookie(new NewCookie[]{new NewCookie("token", responseDto.getToken()), new NewCookie("id", responseDto.getId())})
                    .build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("logout")
    public Response logout(@Context HttpServletRequest request) {
        request.getSession().removeAttribute(AccessAuthDto.PARAM_AUTH_ID);
        request.getSession().removeAttribute(AccessAuthDto.PARAM_AUTH_TOKEN);
        return Response.ok().build();
    }

    @GET
    @Path("isAuthenticated")
    public Response isAuthenticated(@Context HttpServletRequest request) {
        return Response.ok(request.getSession().getAttribute(AccessAuthDto.PARAM_AUTH_ID) != null
                && request.getSession().getAttribute(AccessAuthDto.PARAM_AUTH_TOKEN) != null).build();
    }

    @GET
    @Path("getRoles")
    public Response getRoles() {
        return Response.ok().build();
    }

    @GET
    @Path("getCurrentUser")
    public Response getCurrentUser(@Context HttpServletRequest request) {
        CommonResponse response = new CommonResponse("OK");
        response.setData(userService.findByUsername((String) request.getSession().getAttribute(AccessAuthDto.PARAM_AUTH_ID)));
        return Response.ok(response).build();
    }

    @POST
    @Path("signUp")
    public Response signUp(UserSignUpDto dto) {
        userService.create(dto);
        return Response.ok(new CommonListResponse("OK")).build();
    }

}
