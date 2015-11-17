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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
