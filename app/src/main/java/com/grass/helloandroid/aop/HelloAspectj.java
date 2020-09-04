package com.grass.helloandroid.aop;

import android.util.Log;

/**
 * Created by grassswwang
 * on 2020/9/4
 * Email: grassswwang@tencent.com
 */
public class HelloAspectj {
    public void test() {
        Animal animal = new Animal();
        animal.fly();
    }

    @TimeConsumeLogAnnotation()
    public static void sayHelloWorld(String name) {
        Log.i("grass", "sayHelloWorld: " + name);
    }
}
