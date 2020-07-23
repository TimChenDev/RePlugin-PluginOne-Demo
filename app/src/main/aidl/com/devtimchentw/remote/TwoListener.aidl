package com.devtimchentw.remote;
import com.devtimchentw.remote.TwoToOne;

/**
 * PluginTwo 端的 Listener
 * 讓 One 通知 Two 的介面
 */
interface TwoListener {

    /**
     * 告訴 PluginTwo message
     * 並且給他一個 binder TwoToOne, 可以讓 Two 透過此 Binder 通知 One 一些訊息
     */
    void talkToTwo(String message, TwoToOne tto);
}