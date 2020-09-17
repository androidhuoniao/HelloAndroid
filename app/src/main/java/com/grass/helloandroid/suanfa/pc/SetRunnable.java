package com.grass.helloandroid.suanfa.pc;

/**
 * Created by grassswwang
 * on 2020/9/17
 * Email: grassswwang@tencent.com
 */
public class SetRunnable implements Runnable {
    private Baozi s;

    public SetRunnable(Baozi s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (s) {
                // 判断有没有
                if (s.flag) { // 生产端，有就等待
                    try {
                        System.out.println("生产端：等待。。。");
                        s.wait(); // 等待，并且立即释放锁。将来醒过来的时候，是从这里醒过来的
                        System.out.println("生产端：醒过来了。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 开始生产
                s.count++;
                System.out.println("生产第" + s.count + "包子。。。");

                // 生产完后，修改标记为true
                s.flag = true;
                // 唤醒线程
                s.notifyAll();
                System.out.println("==========开始抢CPU的执行权==========");
            }
        }
    }
}