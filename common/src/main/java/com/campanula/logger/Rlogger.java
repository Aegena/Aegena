package com.campanula.logger;


import androidx.annotation.NonNull;
import com.orhanobut.logger.Logger;

/**
 * package com.campanula.library.logger
 *
 * @author 000286
 * create 2018-10-31
 * desc 使用日志组件com.orhanobut.logger的在封装
 **/
public final class Rlogger {
    private Rlogger() {
    }

    private static class Single {
        static Rlogger mRlogger = new Rlogger();
    }

    public static Rlogger getInstance() {
        return Single.mRlogger;
    }

    /**
     * 添加日志适配
     *
     * @param mLogAdapters 继承自com.orhanobut.logger.LogAdapter interface
     */
    public void initialize(@NonNull RlogAdapter... mLogAdapters) {
        for (RlogAdapter adapter : mLogAdapters) {
            Logger.addLogAdapter(adapter);
        }

    }

    /**
     * 初始化日志系统，默认日志为AndroidAdapter
     */
    public void initialize() {
        initialize(new RAndroidAdapter());
    }
}
