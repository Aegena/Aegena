package com.campanula.https;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResult<T> {

    private static final int SUCCESS_CODE = 10000;

    @JsonProperty
    private int code;
    @JsonProperty
    private String message;
    @JsonProperty
    private T results;

    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public boolean isSuccess() {
        return SUCCESS_CODE == getCode();
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
