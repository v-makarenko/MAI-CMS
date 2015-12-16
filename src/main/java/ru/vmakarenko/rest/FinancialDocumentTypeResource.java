package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.dto.events.PresenceDto;
import ru.vmakarenko.dto.events.financial.FinancialDocumentTypeDto;
import ru.vmakarenko.entities.events.financial.FinancialDocument;
import ru.vmakarenko.entities.events.financial.FinancialDocumentType;
import ru.vmakarenko.services.EventsService;
import ru.vmakarenko.services.FinancialDocumentTypeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/financialDocumentTypes")
@Consumes("application/json")
@Produces("application/json")
public class FinancialDocumentTypeResource {
    @Inject
    private FinancialDocumentTypeService service;


    @GET
    @Path("{id}")
    public Response get(@PathParam("id") UUID id){
        return Response
                .ok(RestResponse.createOk().data(service.find(id)))
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
    public Response insert(FinancialDocumentTypeDto dto){
        service.insert(dto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @PUT
    public Response update(FinancialDocumentTypeDto dto){
        service.update(dto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }


}
