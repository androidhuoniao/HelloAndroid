package com.grass.helloandroid.aop;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by grassswwang
 * on 2020/9/4
 * Email: grassswwang@tencent.com
 */
@Aspect
public class TimeConsumeLogAspectJ {

    private static final String TAG = "1111";
    //通过ThreadLocal隔离不同线程的变量
    ThreadLocal<Long> timeRecord = new ThreadLocal<>();

    @Pointcut("execution(* *(..)) && @annotation(com.grass.helloandroid.aop.TimeConsumeLogAnnotation)")
    public void callMethod() {

    }

    @Before("callMethod()")//③
    public void beforeMethodCall(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log.i(TAG, "beforeMethodCall: method: " + method + " begin");
    }

    @After("callMethod()")//③
    public void afterMethodCall(JoinPoint joinPoint) {
        Log.e(TAG, "before->" + joinPoint.getTarget().toString()); //④
        long beginTime = timeRecord.get();
        System.out.println("方法" + joinPoint.getSignature().getName() + "结束,耗时" + (System.currentTimeMillis() - beginTime) + "ms");
    }
}
