package com.campanula.https;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/11
 * since
 * desc
 **/
public class Client {

    private Client() {
    }


    private static OkHttpClient mOkHttpClient;
    private static HttpLoggingInterceptor mHttpLoggingInterceptor;
    private static Interceptor mHeaderInterceptor;

    private static int mConnectTimeout = 10;
    private static int mReadTimeout = 30;
    private static int mWriteTimeout = 30;

    public synchronized static OkHttpClient get() {

        if (mHttpLoggingInterceptor == null) {
            mHttpLoggingInterceptor = InterceptorUtil.LogInterceptor();
        }

        if (mHeaderInterceptor == null) {
            mHeaderInterceptor = InterceptorUtil.HeaderInterceptor();
        }

        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(mHttpLoggingInterceptor)
                    .addInterceptor(mHeaderInterceptor)
                    .connectTimeout(mConnectTimeout, TimeUnit.SECONDS)
                    .readTimeout(mReadTimeout, TimeUnit.SECONDS)
                    .writeTimeout(mWriteTimeout, TimeUnit.SECONDS)
                    .build();
        }

        return mOkHttpClient;
    }


    public static void setHttpLoggingInterceptor(HttpLoggingInterceptor mHttpLoggingInterceptor) {
        Client.mHttpLoggingInterceptor = mHttpLoggingInterceptor;
        setOkHttpClient(null);
    }


    public static void setOkHttpClient(OkHttpClient mOkHttpClient) {
        Client.mOkHttpClient = mOkHttpClient;
        AsyncRequest.setRetrofit(null);
    }

    public static void setHeaderInterceptor(Interceptor mHeaderInterceptor) {
        Client.mHeaderInterceptor = mHeaderInterceptor;
        setOkHttpClient(null);
    }

    public static void setConnectTimeout(int mConnectTimeout) {
        Client.mConnectTimeout = mConnectTimeout;
        setOkHttpClient(null);
    }

    public static void setReadTimeout(int mReadTimeout) {
        Client.mReadTimeout = mReadTimeout;
        setOkHttpClient(null);
    }

    public static void setWriteTimeout(int mWriteTimeout) {
        Client.mWriteTimeout = mWriteTimeout;
        setOkHttpClient(null);
    }
}
