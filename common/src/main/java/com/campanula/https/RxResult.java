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
public class RxResult<T> implements Function<BaseResult<T>, T> {
    @Override
    public T apply(BaseResult<T> result) {
        if (result.isSuccess()) {
            return result.getResults();
        } else {
            throw new RuntimeException("request error(code = " + result.getCode() + " , message = " + result.isSuccess() + ")");
        }
    }
}
