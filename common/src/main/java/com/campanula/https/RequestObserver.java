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


    public RequestObserver(Builder mBuilder) {
        this.mContext = mBuilder.mContext;
        this.mObserverListener = mBuilder.mObserverListener;
        this.mProgressListener = mBuilder.mProgressListener;
        this.mRequestListener = mBuilder.mRequestListener;

    }

    public static Builder newBuilder(Context mContext) {
        return new Builder(mContext);
    }

    @Override
    public void onSubscribe(Disposable d) {
        mObserverListener.onRequestStart();
        if (mProgressListener != null) {
            mProgressListener.showProgressDialog();
        }
    }

    @Override
    public void onNext(BaseResult<T> mBaseResult) {
        mObserverListener.onResponseEnd();
        if (mBaseResult.isSuccess()) {
            try {
                mRequestListener.onSuccess(mBaseResult.getResults());
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
            mProgressListener.onDismissListener();
        }
    }

    @Override
    public void onError(Throwable e) {
        mObserverListener.onResponseEnd();
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

    public static final class Builder {
        WeakReference<Context> mContext;
        ObserverListener mObserverListener;
        RequestListener mRequestListener;
        ProgressListener mProgressListener;

        public Builder(Context mContext) {
            this.mContext = new WeakReference<>(mContext);
        }

        public Builder setObserverListener(ObserverListener mObserverListener) {
            this.mObserverListener = mObserverListener;
            return this;
        }

        public Builder setRequestListener(RequestListener mRequestListener) {
            this.mRequestListener = mRequestListener;
            return this;
        }

        public Builder setProgressListener(ProgressListener mProgressListener) {
            this.mProgressListener = mProgressListener;
            return this;
        }


        public RequestObserver build() {
            if (mObserverListener == null) {
                this.mObserverListener = new ObserverListener() {
                    @Override
                    public void onRequestStart() {
                        Logger.i("onRequestStart");
                    }

                    @Override
                    public void onResponseEnd() {
                        Logger.i("onResponseEnd");
                    }
                };
            }

            if (mRequestListener == null) {
                this.mRequestListener = new RequestListener() {
                    @Override
                    public void onSuccess(Object results) throws Exception {
                        Logger.i("success");
                    }

                    @Override
                    public void onError(String message, int code) throws Exception {
                        Logger.i(message, code);
                    }

                    @Override
                    public void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        Logger.i(e.getMessage(), isNetWorkError);
                    }
                };
            }
            return new RequestObserver(this);
        }
    }


}
