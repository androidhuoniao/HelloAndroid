package com.grass.helloandroid.epic;

import android.util.Log;

import de.robv.android.xposed.XC_MethodHook;

/**
 * Created by grassswwang
 * on 2020/9/4
 * Email: grassswwang@tencent.com
 */
public class ThreadMethodHook extends XC_MethodHook {
    private static final String TAG = "ThreadMethodHook";

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        Thread t = (Thread) param.thisObject;
        Log.i(TAG, "thread:" + t + ", started..");
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        Thread t = (Thread) param.thisObject;
        Log.i(TAG, "thread:" + t + ", exit..");
    }
}
