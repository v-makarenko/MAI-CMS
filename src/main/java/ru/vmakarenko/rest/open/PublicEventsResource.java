package ru.vmakarenko.rest.open;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.services.EventsService;
import ru.vmakarenko.services.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */

@Path("public/events")
@Consumes("application/json")
@Produces("application/json")
public class PublicEventsResource {

    @Inject
    private EventsService service;

    @GET
    @Path("archive")
    public Response getArchive(){
        return Response
                .ok(RestResponse.createOk().data(service.getPreviousList()))
                .build();
    }

    @GET
    @Path("current")
    public Response getCurrent(){
        return Response
                .ok(RestResponse.createOk().data(service.getCurrent()))
                .build();
    }
}
