package com.grass.helloandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes

/**
 *
 * Created by grassswwang
 * on 2020/7/10
 * Email: grassswwang@tencent.com
 */

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layout, this, attachToRoot)

fun View.getText(@StringRes res: Int) = this.resources.getText(res)
fun View.setVisibleIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}