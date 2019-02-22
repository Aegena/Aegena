package com.campanula.https;

import io.reactivex.functions.Function;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/14
 * since
 * desc
 **/
public class RxResult<T extends BaseResult> implements Function<T, T> {
    @Override
    public T apply(T result) {
        if (result.success()) {
            return result;
        } else {
            throw new RuntimeException(" request  error ");
        }
    }
}
