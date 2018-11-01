package com.campanula.library.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.campanula.library.utils.Utils;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.PrettyFormatStrategy;


/**
 * package com.campanula.library.logger
 *
 * @author 000286
 * create 2018-10-31
 * desc
 **/
public class RAndroidAdapter implements RlogAdapter {
    @NonNull
    private final FormatStrategy formatStrategy;

    public RAndroidAdapter() {
        this.formatStrategy = PrettyFormatStrategy.newBuilder().build();
    }

    public RAndroidAdapter(@NonNull FormatStrategy formatStrategy) {
        this.formatStrategy = Utils.checkNotNull(formatStrategy);
    }

    @Override
    public boolean isLoggable(int priority, @Nullable String tag) {
        return true;
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        formatStrategy.log(priority, tag, message);
    }


}