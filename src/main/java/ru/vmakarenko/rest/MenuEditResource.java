package ru.vmakarenko.rest;

import ru.vmakarenko.dto.common.CommonResponse;
import ru.vmakarenko.dto.menuEdit.MenuEditDto;
import ru.vmakarenko.filters.MenuFilter;
import ru.vmakarenko.services.MenuService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * Created by VMakarenko on 4/25/2015.
 */
@Path("private/menuEdit")
public class MenuEditResource {
    @Inject
    MenuService menuService;

    @Path("getAll")
    @POST
    public Response getAll(MenuFilter filter) {
        return Response.ok(new CommonResponse("OK").data(menuService.getAll(filter))).build();
    }

    @POST
    public Response insert(MenuEditDto menuEditDto) {
        menuService.insert(menuEditDto);
        return Response.ok().build();
    }

    @PUT
    public Response update(MenuEditDto menuEditDto) {
        menuService.update(menuEditDto);
        return Response.ok().build();
    }


    @DELETE
    public Response delete(@QueryParam("id") UUID id) {
        menuService.delete(id);
        return Response.ok().build();
    }

    @GET
    @Path("allDaysOfWeek")
    public Response getAllDaysOfWeek() {
        return Response.ok(menuService.getAllDaysOfWeek()).build();
    }
}

