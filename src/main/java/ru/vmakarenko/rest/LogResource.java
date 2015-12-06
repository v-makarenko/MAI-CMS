package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.dto.events.PresenceDto;
import ru.vmakarenko.services.EventsService;
import ru.vmakarenko.services.LogService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/logs")
@Consumes("application/json")
@Produces("application/json")
public class LogResource {
    @Inject
    private LogService service;

    @GET
    @Path("getAll")
    public Response getAll(@QueryParam("userId") UUID userId,@QueryParam("timeFrom") Date timeFrom,@QueryParam("timeTo") Date timeTo ){
        return Response
                .ok(RestResponse.createOk().data(service.getLog(userId, timeFrom, timeTo)))
                .build();
    }
}
