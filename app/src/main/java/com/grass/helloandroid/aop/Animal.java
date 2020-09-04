package com.grass.helloandroid.aop;

import android.util.Log;

/**
 * Created by grassswwang
 * on 2020/9/4
 * Email: grassswwang@tencent.com
 */
public class Animal {
    public void fly() {
        Log.e("grassaspect", "animal fly method:" + this.toString() + "#fly");
    }
}
