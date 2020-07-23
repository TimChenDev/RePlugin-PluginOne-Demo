package com.devtimchentw.replugin_pluginone_demo

import android.app.Application
import android.util.Log

/**
 *  author: Tim Chen
 *  time  : 2020-07-23
 *  desc  :
 */
class OneApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.v("AidlTest", "OneApplication onCreate")
    }
}