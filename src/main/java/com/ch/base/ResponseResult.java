package com.ch.base;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {

    private int code;
    private String error;
    private String error_description;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(int code, String error, String error_description, T data) {
        this.code = code;
        this.error = error;
        this.error_description = error_description;
        this.data = data;
    }

    public ResponseResult() {
    }
}
