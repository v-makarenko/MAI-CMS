package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.common.EmailTemplateDto;
import ru.vmakarenko.services.EmailDistributionService;
import ru.vmakarenko.services.EmailTemplateService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/emailTemplates")
@Consumes("application/json")
@Produces("application/json")
public class EmailTemplateResource {
    @Inject
    private EmailTemplateService service;

    @Path("/all")
    @GET
    public Response getAllTemplates () {
        return Response.ok(RestResponse.createOk().data(service.getAll())).build();
    }

    @PUT
    public Response update (EmailTemplateDto template) {
        service.update(template);
        return Response.ok(RestResponse.createOk()).build();
    }

    @POST
    public Response save (EmailTemplateDto template) {
        service.save(template);
        return Response.ok(RestResponse.createOk()).build();
    }

    @DELETE
    public Response delete (@QueryParam("id") UUID id) {
        service.delete(id);
        return Response.ok(RestResponse.createOk()).build();
    }
}
