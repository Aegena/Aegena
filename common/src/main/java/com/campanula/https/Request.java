package com.campanula.https;

import android.content.Context;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
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

    static class Singleton {
        static Request mRequest = new Request();
    }

    //    private WeakReference<Context> mContext;
    private Observable mObservable;
    private Observer mObserver;


    public static Request getInstance() {
        return Singleton.mRequest;
    }

//    public Request with(Context mContext) {
//        this.mContext = new WeakReference<>(mContext);
//        return getInstance();
//    }

    public Request setObservable(Observable mObservable) {
        this.mObservable = mObservable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Result());
        return getInstance();
    }


    public void setObserver(@NotNull ObserverListener mObserverListener, @NotNull RequestListener mRequestListener) {
        mObserver = new RequestObserver(mObserverListener, mRequestListener);
        mObservable.subscribe(mObserver);
    }

    public void setObserver(@NotNull Context mContext, @NotNull RequestListener mRequestListener, @NotNull ProgressListener mProgressListener) {
        mObserver = new RequestObserver(mContext, mRequestListener, mProgressListener);
        mObservable.subscribe(mObserver);
    }

    public void setObserver(@NotNull Context mContext, @NotNull ObserverListener mObserverListener, @NotNull RequestListener mRequestListener, @NotNull ProgressListener mProgressListener) {
        mObserver = new RequestObserver(mContext, mObserverListener, mRequestListener, mProgressListener);
        mObservable.subscribe(mObserver);
    }
}
