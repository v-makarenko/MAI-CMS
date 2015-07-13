package ru.vmakarenko.dto.common;

import java.util.List;

/**
 * Created by vmakarenko on 22.04.2015.
 */
public class CommonListResponse {
    private String result;
    private String msg;
    private List<AbstractDto> data;


    public CommonListResponse(String result) {
        this(result, null);
    }

    public CommonListResponse(String result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<AbstractDto> getData() {
        return data;
    }

    public void setData(List<AbstractDto> data) {
        this.data = data;
    }
    public CommonListResponse data(List<AbstractDto> data) {
        this.data = data; return this;
    }
}
