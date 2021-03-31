package com.fls.self_care_assistant.app

import android.app.Application
import androidx.lifecycle.LifecycleObserver


class App : Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @JvmStatic
        lateinit var instance: App
    }
}