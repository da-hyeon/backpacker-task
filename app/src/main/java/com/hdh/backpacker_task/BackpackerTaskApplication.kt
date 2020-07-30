package com.hdh.backpacker_task

import android.app.Application
import com.facebook.stetho.Stetho


class BackpackerTaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this)
        }
    }
}