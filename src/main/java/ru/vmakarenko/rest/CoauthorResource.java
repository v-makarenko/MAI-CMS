package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.events.ThesisDto;
import ru.vmakarenko.services.CoauthorService;
import ru.vmakarenko.services.ThesisService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/coauthors")
@Consumes("application/json")
@Produces("application/json")
public class CoauthorResource {
    @Inject
    private CoauthorService service;


    @DELETE
    public Response delete(@QueryParam("id") UUID id) {
        service.delete(id);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }


}
