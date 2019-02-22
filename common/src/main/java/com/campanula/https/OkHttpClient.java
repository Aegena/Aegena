package com.campanula.https;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/11
 * since
 * desc OKhttpClient
 **/
public class OkHttpClient {

    private OkHttpClient() {
    }


    private static okhttp3.OkHttpClient mOkHttpClient;
    private static HttpLoggingInterceptor mHttpLoggingInterceptor;
    private static Interceptor mHeaderInterceptor;

    private static int mConnectTimeout = 10;
    private static int mReadTimeout = 30;
    private static int mWriteTimeout = 30;

    public synchronized static okhttp3.OkHttpClient get() {

        if (mHttpLoggingInterceptor == null) {
            mHttpLoggingInterceptor = InterceptorUtil.LogInterceptor();
        }

        if (mHeaderInterceptor == null) {
            mHeaderInterceptor = InterceptorUtil.HeaderInterceptor();
        }

        if (mOkHttpClient == null) {
            mOkHttpClient = new okhttp3.OkHttpClient.Builder()
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
        OkHttpClient.mHttpLoggingInterceptor = mHttpLoggingInterceptor;
        setOkHttpClient(null);
    }


    public static void setOkHttpClient(okhttp3.OkHttpClient mOkHttpClient) {
        OkHttpClient.mOkHttpClient = mOkHttpClient;
        RetrofitClient.setRetrofit(null);
    }

    public static void setHeaderInterceptor(Interceptor mHeaderInterceptor) {
        OkHttpClient.mHeaderInterceptor = mHeaderInterceptor;
        setOkHttpClient(null);
    }

    public static void setConnectTimeout(int mConnectTimeout) {
        OkHttpClient.mConnectTimeout = mConnectTimeout;
        setOkHttpClient(null);
    }

    public static void setReadTimeout(int mReadTimeout) {
        OkHttpClient.mReadTimeout = mReadTimeout;
        setOkHttpClient(null);
    }

    public static void setWriteTimeout(int mWriteTimeout) {
        OkHttpClient.mWriteTimeout = mWriteTimeout;
        setOkHttpClient(null);
    }
}
