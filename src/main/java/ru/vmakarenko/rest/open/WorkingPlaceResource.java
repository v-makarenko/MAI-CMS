package ru.vmakarenko.rest.open;


import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.dto.common.WorkingPlaceDto;
import ru.vmakarenko.dto.helper.WPConnectDto;
import ru.vmakarenko.services.WPService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by vmakarenko on 22.04.2015.
 */
@Path("/open/wplaces")
@Consumes("application/json")
@Produces("application/json")
public class WorkingPlaceResource {
    @Inject
    private WPService wpService;


    @Path("getAll")
    @GET
    public Response getAll(){
        return Response
                .ok(RestResponse.createOk().data(wpService.getAll()))
                .build();
    }

}
