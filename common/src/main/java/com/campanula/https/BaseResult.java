package com.campanula.https;


public abstract class BaseResult {

    public abstract boolean success();

    public abstract String message();

    public abstract Object getData();

}
