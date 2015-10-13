package ru.vmakarenko.exceptions;

import ru.vmakarenko.common.RestResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by VMakarenko on 10/13/2015.
 */
public class MaiWebappException extends WebApplicationException {
    private String code;
    private String message;
    private Object data;

    public MaiWebappException(String code, String message, Object data) {
        super(Response.ok(RestResponse.createError(code).errMsg(message).data(data)).build());
    }
    public MaiWebappException(String message) {
        super(Response.ok(RestResponse.createError("").errMsg(message)).build());
    }
}
