package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.dto.events.PresenceDto;
import ru.vmakarenko.dto.events.financial.FinancialDocumentDto;
import ru.vmakarenko.dto.events.financial.FinancialDocumentTypeDto;
import ru.vmakarenko.dto.findocs.CreateForUserDto;
import ru.vmakarenko.services.EventsService;
import ru.vmakarenko.services.FinancialDocumentService;
import ru.vmakarenko.services.FinancialDocumentTypeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/financialDocuments")
@Consumes("application/json")
@Produces("application/json")
public class FinancialDocumentResource {
    @Inject
    private FinancialDocumentService service;


    @GET
    @Path("forEvent/{id}")
    public Response getForEvent(@PathParam("id") UUID id){
        return Response
                .ok(RestResponse.createOk().data(service.getForEvent(id)))
                .build();
    }

    @GET
    @Path("forUser")
    public Response getForUser(@QueryParam("eventId") UUID eventId, @QueryParam("userId") UUID userId){
        return Response
                .ok(RestResponse.createOk().data(service.getForUser(eventId, userId)))
                .build();
    }


    @GET
    public Response getAll(){
        return Response
                .ok(RestResponse.createOk().data(service.getAll()))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id){
        service.delete(id);
        return Response.ok().build();
    }

    @POST
    public Response insert(FinancialDocumentDto dto){
        service.insert(dto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @PUT
    public Response update(FinancialDocumentDto dto){
        service.update(dto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @POST
    @Path("createDocsForUser")
    public Response createDocsForUser(CreateForUserDto dto){
        service.createDocsForUser(dto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }


}
