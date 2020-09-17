package com.grass.helloandroid.looper

import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast


/**
 *
 * Created by grassswwang
 * on 2020/9/15
 * Email: grassswwang@tencent.com
 */
object HelloLooper {
    fun startLooper(context: Context) {
        Thread {
            Log.e("qdx", "step 0 ")
            Looper.prepare()
            Toast.makeText(context, "run on Thread", Toast.LENGTH_SHORT).show()
            Log.e("qdx", "step 1 ")
            Looper.loop()
            Log.e("qdx", "step 2 ")
        }.start()
    }
}