package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.services.EmailDistributionService;
import ru.vmakarenko.services.EmailService;
import ru.vmakarenko.services.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/email")
@Consumes("application/json")
@Produces("application/json")
public class EmailResource {
    @Inject
    private EmailDistributionService service;

    @Path("/distribute")
    @POST
    public Response distributeMsgs () {
//        service.distributeWithTemplate(UUID.fromString("b3c99e20-30a4-455f-9d9d-0564ea9b95ee"));
        return Response.ok(RestResponse.createOk()).build();
    }
}
