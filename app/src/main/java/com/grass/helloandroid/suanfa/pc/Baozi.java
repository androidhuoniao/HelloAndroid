package com.grass.helloandroid.suanfa.pc;

/**
 * Created by grassswwang
 * on 2020/9/17
 * Email: grassswwang@tencent.com
 */

/*
 * 包子生产消费案例：一次生产或消费一个包子，有包子就消费，没有就生产。
 */
public class Baozi {
    // 默认是flag，表示没有包子，需要生产线程来生产包子；如果是true，说明有包子，需要消费端来消费包子。
    public boolean flag;

    // 计数，当前在生产或消费第n个包子
    public int count = 0;

    public static void test() {
        // 创建共享对象
        Baozi s = new Baozi();

        // 在外界把共享对象创建出来，通过构造方法传递给其他的类。这样st、gt1、gt2就共享s对象。
        SetRunnable st = new SetRunnable(s);
        GetRunnable gt1 = new GetRunnable(s);
        GetRunnable gt2 = new GetRunnable(s);

        // 线程类
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(gt1);
        Thread t3 = new Thread(gt2);

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}