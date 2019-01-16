package com.campanula.https;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * package com.campanula.library
 *
 * @author campanula
 * create 2018-11-15
 * desc
 **/
public class AsyncRequest {

    private AsyncRequest() {
    }

    private static String mBaseUrl;

    private static Retrofit mRetrofit;

    public static Retrofit get() {
        if (mBaseUrl == null) {
            mBaseUrl = "http://www.kuaidi100.com";
        }
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .client(Client.get())
//                    .addConverterFactory(JacksonConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public static <T> T create(Class<T> tClass) {
        return get().create(tClass);
    }


    public static void setBaseUrl(String mBaseUrl) {
        AsyncRequest.mBaseUrl = mBaseUrl;
        setRetrofit(null);
    }

    public static void setRetrofit(Retrofit mRetrofit) {
        AsyncRequest.mRetrofit = mRetrofit;
    }
}
