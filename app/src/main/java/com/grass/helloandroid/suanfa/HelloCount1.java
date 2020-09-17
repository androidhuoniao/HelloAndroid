package com.grass.helloandroid.suanfa;

import android.util.Log;

/**
 * Created by grassswwang
 * on 2020/9/15
 * Email: grassswwang@tencent.com
 */
public class HelloCount1 {
    public static void test() {
        int num = 100;
        int count = times1(num);
        Log.i("suanfa", "times1: count: " + count);

        int count2 = times2(num);
        Log.i("suanfa", "times2: count: " + count);
    }

    /**
     * 逐个检查n的每一位是否为1。
     * 时间复杂度与x的bit位数相关
     * 例如n的二进制为1011
     * 第一次1011 & 0001
     * 第二次 1011 & 0010
     * @param n
     * @return
     */
    public static int times1(int n) {
        int count = 0;
        int flag = 1;
        while (flag <= n) {
            if ((n & flag) != 0)
                count++;
            flag = flag << 1;
        }
        return count;
    }

    public static int times2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

}
