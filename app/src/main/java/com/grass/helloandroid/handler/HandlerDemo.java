package com.grass.helloandroid.handler;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by grassswwang
 * on 2020/10/6
 * Email: grassswwang@tencent.com
 */
public class HandlerDemo {
    public void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();
                handler.sendEmptyMessage(0);
                Looper.loop();
            }
        }).start();

    }
}
