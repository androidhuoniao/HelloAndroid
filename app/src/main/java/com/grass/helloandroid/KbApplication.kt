package com.grass.helloandroid

import android.app.Application

/**
 *
 * Created by grassswwang
 * on 2020/7/9
 * Email: grassswwang@tencent.com
 */
class KbApplication : Application() {

    companion object {
        var application: Application = Application();
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}