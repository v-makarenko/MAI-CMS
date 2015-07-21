package ru.vmakarenko.rest;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.common.MessageDto;
import ru.vmakarenko.dto.users.UserDto;
import ru.vmakarenko.services.MessageService;
import ru.vmakarenko.services.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/private/message")
@Consumes("application/json")
@Produces("application/json")
public class MessageResource {
    @Inject
    private MessageService msgService;

    @Path("incoming")
    @GET
    public Response getAllIncoming(){
        return Response
                .ok(RestResponse.createOk().data(msgService.getAllIncoming()))
                .build();
    }

    @Path("send")
    @POST
    public Response sendMsg(MessageDto messageDto){
        msgService.sendMsg(messageDto);
        return Response
                .ok(RestResponse.createOk())
                .build();
    }


}
