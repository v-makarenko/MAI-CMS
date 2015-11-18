package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dao.SectionDao;
import ru.vmakarenko.dto.common.SectionDto;
import ru.vmakarenko.dto.common.WorkingPlaceDto;
import ru.vmakarenko.services.SectionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/sections")
@Consumes("application/json")
@Produces("application/json")
public class SectionResource {
    @Inject
    private SectionService service;


    @Path("getAll")
    @GET
    public Response getAll(@QueryParam("eventId") UUID eventId){
        return Response
                .ok(RestResponse.createOk().data(service.getAll(eventId)))
                .build();
    }
    @POST
    public Response createUser(SectionDto dto){
        service.create(dto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @PUT
    public Response update(SectionDto dto){
        service.update(dto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }
    @DELETE
    public Response delete(@QueryParam("id") UUID id){
        service.delete(id);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }
}
