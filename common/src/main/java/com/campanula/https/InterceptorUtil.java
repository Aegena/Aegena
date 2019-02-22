package com.campanula.https;

import com.campanula.logger.Logger;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/10
 * since
 * desc
 **/
public class InterceptorUtil {

    private final static String LEFT_BRACKETS = "{";
    private final static String RIGHT_BRACKETS = "}";

    public static HttpLoggingInterceptor LogInterceptor() {
        // 设置打印数据的级别
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                // 打印retrofit日志
                if (message.startsWith(LEFT_BRACKETS) && message.endsWith(RIGHT_BRACKETS)) {
                    Logger.json(message);
                } else {
                    Logger.v(message);
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);

    }

    public static okhttp3.Interceptor HeaderInterceptor() {
        return new okhttp3.Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request mRequest = chain.request();
                // 在这里你可以做一些想做的事,比如token失效时,重新获取token
                // 或者添加header等等
                return chain.proceed(mRequest);
            }
        };
    }


}
