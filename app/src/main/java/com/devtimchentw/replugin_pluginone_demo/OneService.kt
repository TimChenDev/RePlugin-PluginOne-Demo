package com.devtimchentw.replugin_pluginone_demo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.devtimchentw.remote.OneListener
import com.devtimchentw.remote.TwoListener
import com.devtimchentw.remote.TwoToOne
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
                "PluginOne 的 updateAppInfo 這裡收到通知囉",
                Toast.LENGTH_LONG
            ).show()

            // do something...
            doSomething()

            // fetchBinder 會去檢查 該插件是否存在, 會做插件安裝的動作, 再去取得 IBinder
            // 這裡就會把 pluginTwo 給拉起, 並且取得 twoListener 的 instance
            val binder = RePlugin.fetchBinder("com.devtimchentw.replugin_plugintwo_demo", "ITwo")
            binder?.run {
                val twoListener = TwoListener.Stub.asInterface(binder)
                // 通知 PluginTwo, 並且提供一個 binder(twoToOne) 給
                twoListener.talkToTwo("Hi PluginTwo, 你可以透過 TwoToOne 回傳訊息給 PluginOne 喔", twoToOne)
            } ?: run {
                Log.v("AidlTest", "PluginOne >> OneService >> updateAppInfo() binder is null")
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        // 提供給另一端的 binder instance
        return binder
    }

    val twoToOne : TwoToOne.Stub = object: TwoToOne.Stub() {
        override fun backFromTwo(message: String?) {

            // 從 Two 回傳給 One 的訊息
            Log.v("AidlTest", "PluginOne >> OneService >> twoToOne >> backFromTwo()")
        }
    }

    // region 做雜事

    private fun doSomething() {
        Log.v("AidlTest", "PluginOne >> OneService >> doSomething()")

        for (i in 0..999) {
            Log.v("AidlLogV", "PluginOne >> OneService >> i: $i")
        }
    }

    // endregion
}