package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.dto.events.EventDto;
import ru.vmakarenko.dto.events.PresenceDto;
import ru.vmakarenko.entities.events.Event;
import ru.vmakarenko.services.EventsService;
import ru.vmakarenko.services.MessageService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/events")
@Consumes("application/json")
@Produces("application/json")
public class EventsResource {
    @Inject
    private EventsService service;


    @GET
    @Path("{id}")
    public Response get(@PathParam("id") UUID id){
        return Response
                .ok(RestResponse.createOk().data(service.get(id)))
                .build();
    }


    @GET
    public Response getAll(){
        return Response
                .ok(RestResponse.createOk().data(service.getAll()))
                .build();
    }

    @GET
    @Path("next")
    public Response getNext(){
        return Response
                .ok(RestResponse.createOk().data(service.getNextList()))
                .build();
    }

    @GET
    @Path("previous")
    public Response getPrevious(){
        return Response
                .ok(RestResponse.createOk().data(service.getPreviousList()))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id){
        service.delete(id);
        return Response.ok().build();
    }

    @POST
    public Response save(EventDto dto){
        EventDto result = service.save(dto);
//        result.setUserList(null);
        return Response
                .ok(RestResponse.createOk()
                        .data(result))
                .build();
    }

    @POST
    @Path("/setPresence")
    public Response setPresence(PresenceDto dto){
        service.setPresence(dto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }

    @PUT
    public Response update(EventDto dto){

        return Response
                .ok(RestResponse.createOk().data(service.save(dto)))
                .build();
    }


}
