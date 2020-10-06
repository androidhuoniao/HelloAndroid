package com.grass.helloandroid;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by grassswwang
 * on 2020/9/17
 * Email: grassswwang@tencent.com
 */
public class BFS {

    // DFS 深度优先遍历
    ArrayList<View> getAllChildren(View v) {
        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }
        ArrayList<View> result = new ArrayList<View>();
        ViewGroup group = (ViewGroup) v;
        for (int i = 0; i < group.getChildCount(); i++) {
            View child = group.getChildAt(i);
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));
            result.addAll(viewArrayList);
        }
        return result;
    }

    // BFS 广度优先遍历
    ArrayList<View> getAllChildren2(View v) {
        ArrayList<View> visited = new ArrayList<View>();
        ArrayList<View> unvisited = new ArrayList<View>();
        unvisited.add(v);

        while (!unvisited.isEmpty()) {
            View child = unvisited.remove(0);
            visited.add(child);
            if (!(child instanceof ViewGroup)) {
                continue;
            }
            ViewGroup group = (ViewGroup) child;
            final int childCount = group.getChildCount();
            for (int i = 0; i < childCount; i++) {
                unvisited.add(group.getChildAt(i));
            }
        }

        return visited;
    }
}
