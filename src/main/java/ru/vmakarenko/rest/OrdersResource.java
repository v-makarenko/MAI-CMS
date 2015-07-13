package ru.vmakarenko.rest;

import ru.vmakarenko.dto.common.CommonResponse;
import ru.vmakarenko.filters.OrderFilter;
import ru.vmakarenko.services.OrderService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Path("private/orders")
@Consumes("application/json")
@Produces("application/json")
public class OrdersResource {
    @Inject
    OrderService orderService;

    @POST
    @Path("getAll")
    public Response getAll(OrderFilter filter){
        return Response.ok(new CommonResponse("OK").data(orderService.getAll(filter))).build();
    }
}
