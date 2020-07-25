package com.grass.helloandroid

import androidx.fragment.app.Fragment

/**
 *
 * Created by grassswwang
 * on 2020/7/25
 * Email: grassswwang@tencent.com
 */
interface IFragmentSwitcher {
    fun showFragment(action: Int, fragment: Fragment)
}