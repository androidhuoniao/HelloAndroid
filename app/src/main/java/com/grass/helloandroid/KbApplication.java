package com.grass.helloandroid;

import android.app.Application;

import com.grass.helloandroid.epic.EpicInit;

/**
 * Created by grassswwang
 * on 2020/9/4
 * Email: grassswwang@tencent.com
 */
public class KbApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new EpicInit().init();
    }
}
