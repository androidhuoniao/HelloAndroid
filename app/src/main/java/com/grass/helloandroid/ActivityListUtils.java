package com.grass.helloandroid;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grassswwang
 * on 2020/9/17
 * Email: grassswwang@tencent.com
 */
public class ActivityListUtils {
    private static final ActivityListUtils ourInstance = new ActivityListUtils();

    private List<Activity> activities = new ArrayList<>();
    public static ActivityListUtils getInstance() {
        return ourInstance;
    }

    private ActivityListUtils() {
    }

    public void addActivity(Activity activity) {
       activities.add(activity);
    }
}
