package com.grass.helloandroid.aop;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by grassswwang
 * on 2020/9/4
 * Email: grassswwang@tencent.com
 */
@Aspect
public class MethodAspect {
    private static final String TAG = "MethodAspect";

    @Pointcut("call(* com.grass.helloandroid.aop.Animal.fly(..))")//②
    public void callMethod() {
    }

    @Before("callMethod()")//③
    public void beforeMethodCall(JoinPoint joinPoint) {
        Log.e(TAG, "before->" + joinPoint.getTarget().toString()); //④
    }

}
