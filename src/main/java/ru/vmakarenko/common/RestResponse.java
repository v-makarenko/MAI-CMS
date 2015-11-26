package ru.vmakarenko.common;

/**
 * Created by VMakarenko on 7/15/2015.
 */
public class RestResponse {



    public static class ErrorCodes{
        public static final String NOT_AUTHENTICATED = "NOT_AUTH";
        public static final String PASSWORD_NOT_MATCH = "PWD_MTCH";
    }

    private RestResponse(){}

    public static final String OK_RESPONSE = "OK";
    public static final String ERROR_RESPONSE = "ERROR";

    private String status;
    private String errMsg;
    private String errCode;
    private String stackTrace;
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RestResponse status(String status) {
        this.status = status;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public RestResponse errMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public RestResponse errCode(String errCode) {
        this.errCode = errCode;
        return this;
    }

    public RestResponse stackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }

    public RestResponse data(Object data) {
        this.data = data;
        return this;
    }

    public static RestResponse createOk(){
        RestResponse response = new RestResponse().status(OK_RESPONSE);
        return response;
    }

    public static RestResponse createError(String errCode){
        RestResponse response = new RestResponse()
                .status(ERROR_RESPONSE)
                .errCode(errCode);
        return response;
    }
}
