package com.grass.helloandroid.touchevent;

import android.util.Log;

/**
 * Created by grassswwang
 * on 2020/9/18
 * Email: grassswwang@tencent.com
 */
public class TestGroupFlag {
    protected int mGroupFlags;
    protected static final int FLAG_DISALLOW_INTERCEPT = 0x80000;

    public static void test() {
        TestGroupFlag demo = new TestGroupFlag();
        demo.resetTouchState();
        Log.i("groupflag", "test: " + demo.compute());

    }

    private void resetTouchState() {
        mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
    }

    private boolean compute() {
        final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
        return disallowIntercept;
    }


}
