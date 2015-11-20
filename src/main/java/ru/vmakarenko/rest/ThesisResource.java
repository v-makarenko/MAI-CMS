package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.events.ThesisDto;
import ru.vmakarenko.dto.users.ChangePasswordDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.services.ThesisService;
import ru.vmakarenko.services.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/thesises")
@Consumes("application/json")
@Produces("application/json")
public class ThesisResource {
    @Inject
    private ThesisService service;


    @Path("waitingForYourConfirmation")
    @GET
    public Response getWaitingForYourConfirmation(@QueryParam("eventId") UUID eventId, @QueryParam("userId") UUID userId){
        return Response
                .ok(RestResponse.createOk().data(service.getWaitingConfirmationFromYou(eventId, userId)))
                .build();
    }

    @Path("waitingForCoauthorConfirmation")
    @GET
    public Response getWaitingForCoauthorConfirmation(@QueryParam("eventId") UUID eventId, @QueryParam("userId") UUID userId){
        return Response
                .ok(RestResponse.createOk().data(service.getWaitingConfirmationFromCoauthors(eventId, userId)))
                .build();
    }

    @Path("confirmed")
    @GET
    public Response getConfirmed(@QueryParam("eventId") UUID eventId, @QueryParam("userId") UUID userId){
        return Response
                .ok(RestResponse.createOk().data(service.getConfirmed(eventId, userId)))
                .build();
    }

    @POST
    public Response create(ThesisDto thesisDto){
        service.create(thesisDto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @PUT
    @Deprecated
    public Response update(ThesisDto thesisDto){
        service.update(thesisDto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }


}
