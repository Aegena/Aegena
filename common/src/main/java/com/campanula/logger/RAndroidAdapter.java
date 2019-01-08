package com.campanula.logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.campanula.utils.NullUtils;
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
        this.formatStrategy = NullUtils.checkNotNull(formatStrategy);
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
