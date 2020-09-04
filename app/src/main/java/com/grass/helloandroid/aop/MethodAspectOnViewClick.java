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
public class MethodAspectOnViewClick {
    private static final String TAG = "MethodAspectOnViewClick";

    @Pointcut("execution(* android.view.View.OnClickListener+.onClick(..))")
    public void callMethod() {
    }

    @Before("callMethod()")
    public void beforeMethodCall(JoinPoint joinPoint) {
        Log.e(TAG, "埋点");
    }

}
