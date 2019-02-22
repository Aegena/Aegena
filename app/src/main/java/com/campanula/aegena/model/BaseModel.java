package com.campanula.aegena.model;

import com.campanula.https.BaseResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * package com.campanula.aegena
 *
 * @author campanula
 * date 2019/2/20
 * desc
 **/
@JsonIgnoreProperties
public class BaseModel<T> extends BaseResult {

    @JsonProperty("data")
    private T data;

    @Override
    public boolean success() {
        return true;
    }

    @Override
    public String message() {
        return "error";
    }

    @Override
    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
