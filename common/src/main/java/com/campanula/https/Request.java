package com.campanula.https;

import android.content.Context;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/14
 * since
 * desc
 **/
public class Request {

    private Request() {
    }

    public static Request getInstance() {
        return new Request();
    }

    private Observable mObservable;
    private ObserverListener mObserverListener;
    private RequestListener mRequestListener;
    private ProgressListener mProgressListener;

    public Request setObservable(Observable mObservable) {
        this.mObservable = mObservable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new RxResult());
        return this;
    }

    public Request setObserverListener(ObserverListener mObserverListener) {
        this.mObserverListener = mObserverListener;
        return this;
    }

    public Request setRequestListener(RequestListener mRequestListener) {
        this.mRequestListener = mRequestListener;
        return this;
    }

    public Request setProgressListener(ProgressListener mProgressListener) {
        this.mProgressListener = mProgressListener;
        return this;
    }

    public void with(Context mContext) {

        if (this.mObservable == null) {
            throw new RuntimeException("must init mObservable, you use setObservable method ");
        }


        this.mObservable.subscribe(
                RequestObserver.newBuilder(mContext)
                        .setObserverListener(this.mObserverListener)
                        .setProgressListener(this.mProgressListener)
                        .setRequestListener(this.mRequestListener)
                        .build());
    }
}
