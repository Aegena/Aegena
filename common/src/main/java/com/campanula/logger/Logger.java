package com.campanula.logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.orhanobut.logger.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * package com.campanula.logger
 *
 * @author 000286
 * create 2018-10-31
 * desc 使用日志组件com.orhanobut.logger的在封装
 **/
public final class Logger {
    private Logger() {
        initialize();
    }

    private static final String TAG = "AndroidLogger";

    private static class Single {
        static Logger mLogger = new Logger();
    }

    public static Logger getInstance() {
        return Single.mLogger;
    }

    /**
     * 添加日志适配
     *
     * @param mLogAdapters 继承自com.orhanobut.logger.LogAdapter interface
     */
    private void initialize(@NonNull LogAdapter... mLogAdapters) {
        for (LogAdapter adapter : mLogAdapters) {
            com.orhanobut.logger.Logger.addLogAdapter(adapter);
        }
    }

    /**
     * 初始化日志系统，默认日志为AndroidAdapter
     */
    private void initialize() {

        LogAdapter mAndroidLogAdapter = new AndroidLogAdapter(PrettyFormatStrategy.newBuilder()
                .methodCount(5)
                .tag(TAG)
                .methodOffset(7)
                .logStrategy(new LogcatLogStrategy())
                .showThreadInfo(true)
                .build());

        LogAdapter mCsvLogAdapter = new DiskLogAdapter(CsvFormatStrategy.newBuilder()
                .date(Calendar.getInstance().getTime())
                .dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()))
                .tag(TAG)
                .build());

        initialize(mAndroidLogAdapter, mCsvLogAdapter);
    }

    public static void d(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.d(message, args);
    }

    public static void d(@Nullable Object object) {
        com.orhanobut.logger.Logger.d(object);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.e(message, args);
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.e(throwable, message, args);
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.w(message, args);
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    public static void wtf(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        com.orhanobut.logger.Logger.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        com.orhanobut.logger.Logger.xml(xml);
    }

}
