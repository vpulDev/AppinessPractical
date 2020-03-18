package com.app.task.base

import android.app.Application
import android.content.Context
import com.app.task.BuildConfig
import timber.log.Timber

class BaseApplication : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    companion object {
        private var instance: BaseApplication? = null

        fun getApplicationContext(): Context {
            return instance?.applicationContext!!
        }
    }
}