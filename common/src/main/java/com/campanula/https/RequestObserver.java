package com.campanula.https;

import android.accounts.NetworkErrorException;
import android.content.Context;
import com.campanula.logger.Logger;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/**
 * package com.campanula.https
 *
 * @author campanula
 * create 2019/1/10
 * since
 * desc
 **/
public class RequestObserver<T> implements Observer<BaseResult<T>> {

    private WeakReference<Context> mContext;
    private ObserverListener mObserverListener;
    private RequestListener mRequestListener;
    private ProgressListener mProgressListener;

    public RequestObserver(@NotNull ObserverListener mObserverListener, @NotNull RequestListener mRequestListener) {
        this.mObserverListener = mObserverListener;
        this.mRequestListener = mRequestListener;
    }

    public RequestObserver(@NotNull Context mContext, @NotNull RequestListener mRequestListener, @NotNull ProgressListener mProgressListener) {
        this.mContext = new WeakReference<>(mContext);
        this.mRequestListener = mRequestListener;
        this.mProgressListener = mProgressListener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (mObserverListener != null) {
            mObserverListener.onRequestStart();
        }
        if (mProgressListener != null) {
            mProgressListener.showProgressDialog();
        }
    }

    @Override
    public void onNext(BaseResult<T> mBaseResult) {
        if (mObserverListener != null) {
            mObserverListener.onResponseEnd();
        }

        if (mBaseResult.isSuccess()) {
            try {
                mRequestListener.onSuccees(mBaseResult.getResults());
            } catch (Exception e) {
                e.printStackTrace();
                Logger.e(e, "success");
            }
        } else {
            try {
                mRequestListener.onError(mBaseResult.getMessage(), mBaseResult.getCode());
            } catch (Exception e) {
                e.printStackTrace();
                Logger.e(e, "error");
            }
        }

        if (mProgressListener != null) {
            mProgressListener.closeProgressDialog();
        }
    }

    @Override
    public void onError(Throwable e) {

        if (mObserverListener != null) {
            mObserverListener.onResponseEnd();
        }

        try {
            if (e instanceof ConnectException || e instanceof TimeoutException || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                mRequestListener.onFailure(e, true);
            } else {
                mRequestListener.onFailure(e, false);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Logger.e(exception, "onError");
        }
        if (mProgressListener != null) {
            mProgressListener.onDismissListener();
        }

    }

    @Override
    public void onComplete() {

    }


}
