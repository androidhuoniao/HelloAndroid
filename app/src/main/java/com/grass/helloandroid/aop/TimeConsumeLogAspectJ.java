package com.grass.helloandroid.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by grassswwang
 * on 2020/9/4
 * Email: grassswwang@tencent.com
 */
@Aspect
public class TimeConsumeLogAspectJ {

    @Pointcut("execution(* *(..)) && @annotation(cn.freekiddo.annotation.TimeConsumeLogAnnotation)")
//    @Pointcut("call(* com.wandering.sample.aspectj.Animal.fly(..))")//②
    public void callMethod() {
    }
}
