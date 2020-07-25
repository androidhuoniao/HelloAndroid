package com.grass.helloandroid.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.grass.helloandroid.R

/**
 *
 * Created by grassswwang
 * on 2020/7/25
 * Email: grassswwang@tencent.com
 */
class AnimationActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        showFragment(AnimationListFragment())
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content, fragment)
            .commit()
    }

}