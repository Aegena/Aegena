package com.campanula.library.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * package com.campanula.library.utils
 *
 * @author 000286
 * create 2018-10-31
 * desc
 **/
public final class NullUtils {
    /**
     * 非空检测校验
     *
     * @param obj 待检测的值
     * @param <T> 返回的类型
     * @return 非空时返回，否则抛出异常
     */
    @NonNull
    public static <T> T checkNotNull(@Nullable final T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }
}
