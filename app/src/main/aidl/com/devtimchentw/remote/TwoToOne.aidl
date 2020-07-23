package com.devtimchentw.remote;

/**
 * PluginOne 端的 Listener
 * 用來接收 Two 回傳的訊息
 */
interface TwoToOne {
    void backFromTwo(String message);
}