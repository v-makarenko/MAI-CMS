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
    public Response getWaitingForYourConfirmation(@QueryParam("eventId") UUID eventId, @QueryParam("userId") UUID userId) {
        return Response
                .ok(RestResponse
                        .createOk()
                        .data(service.getWaitingConfirmationFromYou(eventId, userId)))
                .build();
    }

    @Path("waitingForCoauthorConfirmation")
    @GET
    public Response getWaitingForCoauthorConfirmation(@QueryParam("eventId") UUID eventId, @QueryParam("userId") UUID userId) {
        return Response
                .ok(RestResponse
                        .createOk()
                        .data(service.getWaitingConfirmationFromCoauthors(eventId, userId)))
                .build();
    }

    @Path("confirmed")
    @GET
    public Response getConfirmed(@QueryParam("eventId") UUID eventId, @QueryParam("userId") UUID userId) {
        return Response
                .ok(RestResponse
                        .createOk()
                        .data(service.getConfirmed(eventId, userId)))
                .build();
    }

    @Path("getByEvent")
    @GET
    public Response getByEvent(@QueryParam("eventId") UUID eventId, @QueryParam("status") String status) {
        return Response
                .ok(RestResponse
                        .createOk()
                        .data(service.getByEvent(eventId, status)))
                .build();
    }

    @POST
    public Response create(ThesisDto thesisDto) {
        return Response
                .ok(RestResponse
                        .createOk()
                        .data(service.save(thesisDto)))
                .build();
    }

    @DELETE
    public Response delete(@QueryParam("id") UUID id) {
        service.delete(id);
        return Response
                .ok(RestResponse
                        .createOk())
                .build();
    }

    @POST
    @Path("confirm")
    public Response confirm(@QueryParam("id") UUID id) {
        service.confirm(id);
        return Response
                .ok(RestResponse
                        .createOk())
                .build();
    }

    @DELETE
    @Path("deleteFromCA")
    public Response deleteFromCA(@QueryParam("id") UUID id) {
        service.deleteFromCA(id);
        return Response
                .ok(RestResponse
                        .createOk())
                .build();
    }

    @GET
    public Response find(@QueryParam("id") UUID id) {

        return Response
                .ok(RestResponse
                        .createOk()
                        .data(service.find(id)))
                .build();
    }

    @PUT
    @Deprecated
    public Response update(ThesisDto thesisDto) {
        return Response
                .ok(RestResponse
                        .createOk()
                        .data(service.save(thesisDto)))
                .build();
    }


}
