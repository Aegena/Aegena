package com.campanula.https;

/**
 * package com.campanula.library
 *
 * @author 000286
 * create 2018-11-15
 * desc
 **/
public class AsyncRequest {

    private AsyncRequest() {
    }

    private static class Singleton {
        static final AsyncRequest request = new AsyncRequest();
    }

    public static AsyncRequest getInstance() {
        return Singleton.request;
    }

}
