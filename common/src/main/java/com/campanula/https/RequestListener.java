package com.campanula.https;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/10
 * since
 * desc
 **/
public interface RequestListener<T> {

    void onSuccess(T results) throws Exception;

    void onError(String message, int code) throws Exception;

    void onFailure(Throwable e, boolean isNetWorkError) throws Exception;

}
