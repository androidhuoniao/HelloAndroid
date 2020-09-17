package com.grass.helloandroid.suanfa.pc;

/**
 * Created by grassswwang
 * on 2020/9/17
 * Email: grassswwang@tencent.com
 */
public class GetRunnable implements Runnable {
    private Baozi s;

    public GetRunnable(Baozi s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (s) {
                while (!s.flag) { // 消费端，没有就等待
                    try {
                        System.out.println(Thread.currentThread().getName() + "消费端：等待。。。");
                        s.wait(); // 等待，并且立即释放锁。将来醒过来的时候，是从这里醒过来的
                        System.out.println(Thread.currentThread().getName() + "消费端：醒过来了。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 开始消费
                System.out.println(Thread.currentThread().getName() + "消费第" + s.count + "个包子");

                // 消费完了，修改标记为false
                s.flag = false;
                // 唤醒线程
                s.notifyAll();
                System.out.println("==========开始抢CPU的执行权==========");
            }
        }
    }
}