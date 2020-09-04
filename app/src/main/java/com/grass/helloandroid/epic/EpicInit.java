package com.grass.helloandroid.epic;

import android.util.Log;

import com.taobao.android.dexposed.DexposedBridge;
import com.taobao.android.dexposed.XC_MethodHook;

import dalvik.system.DexFile;

/**
 * Created by grassswwang
 * on 2020/9/4
 * Email: grassswwang@tencent.com
 */
public class EpicInit {
    private static final String TAG = "EpicInit";

    public void init() {
        DexposedBridge.hookAllConstructors(Thread.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                Thread thread = (Thread) param.thisObject;
                Class<?> clazz = thread.getClass();
                if (clazz != Thread.class) {
                    Log.d(TAG, "found class extend Thread:" + clazz);
                    DexposedBridge.findAndHookMethod(clazz, "run", new ThreadMethodHook());
                }
                Log.d(TAG, "Thread: " + thread.getName() + " class:" + thread.getClass() + " is created.");
            }
        });
        DexposedBridge.findAndHookMethod(Thread.class, "run", new ThreadMethodHook());


//        DexposedBridge.findAndHookMethod(DexFile.class, "loadDex", String.class, String.class, int.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                super.beforeHookedMethod(param);
//                String dex = (String) param.args[0];
//                String odex = (String) param.args[1];
//                Log.i(TAG, "load dex, input:" + dex + ", output:" + odex);
//            }
//        });
    }
}
