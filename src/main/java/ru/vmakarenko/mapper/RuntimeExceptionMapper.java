package ru.vmakarenko.mapper;

import ru.vmakarenko.common.RestResponse;
import ru.vmakarenko.exceptions.MaiWebappException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by VMakarenko on 12.11.2015.
 */

public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    public Response toResponse(RuntimeException ex) {
        StackTraceElement[] a = {};
        Arrays.asList(a);

        return Response.ok(RestResponse.createError("0")
                .errMsg(ex.getMessage())
                .stackTrace(new ArrayList<>(Arrays.asList(a))
                        .stream().map(StackTraceElement::toString).collect(Collectors.joining("\n"))))
                .entity(MediaType.APPLICATION_JSON).
                        build();
    }

}

