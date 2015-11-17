package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.common.WorkingPlaceDto;
import ru.vmakarenko.dto.users.ChangePasswordDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.entities.users.WorkingPlace;
import ru.vmakarenko.services.UserService;
import ru.vmakarenko.services.WPService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/wplaces")
@Consumes("application/json")
@Produces("application/json")
public class WPResource {
    @Inject
    private WPService wpService;


    @Path("{id}")
    @GET
    public Response getCurrent(@PathParam("id") UUID id){
        return Response
                .ok(RestResponse.createOk().data(wpService.find(id)))
                .build();
    }

    @Path("getAll")
    @GET
    public Response getAll(){
        return Response
                .ok(RestResponse.createOk().data(wpService.getAll()))
                .build();
    }

    @Path("createUser")
    @POST
    public Response createUser(WorkingPlaceDto wp){
        wpService.create(wp);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @PUT
    public Response update(WorkingPlaceDto wp){
        wpService.update(wp);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }
}
