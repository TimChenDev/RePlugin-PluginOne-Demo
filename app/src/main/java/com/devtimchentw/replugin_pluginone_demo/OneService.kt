package com.devtimchentw.replugin_pluginone_demo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.devtimchentw.remote.OneListener
import com.qihoo360.replugin.RePlugin

/**
 *  author: Tim Chen
 *  time  : 2020-07-22
 *  desc  :
 */

class OneService : Service() {

    /**
     * 實現 OneListener 的介面
     * oneListener 作為一個接收器, 在 Service 被 Host 建立後
     * Host 即可通知 PluginOne 去做事情
     */
    private val binder: OneListener.Stub = object : OneListener.Stub() {
        override fun updateAppInfo() {

            // 收到 MainActivity 的通知，要更新 App 資料
            Log.v("AidlTest", "PluginOne >> OneService >> oneListener.updateAppInfo() 收到通知囉")

            // show Toast
            Toast.makeText(
                RePlugin.getHostContext(),
                "updateAppInfo 這裡收到通知囉",
                Toast.LENGTH_LONG
            ).show()

            // Todo do something...
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        // 提供給另一端的 binder instance
        return binder
    }

}