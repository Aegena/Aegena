package com.campanula.aegena

import androidx.multidex.MultiDexApplication
import com.campanula.logger.Logger

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Logger.getInstance()
    }
}