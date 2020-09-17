package com.grass.helloandroid

import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        img.animation = AnimationUtils.loadAnimation(this, R.anim.loading_spin)
        Log.i("test", "SecondActivity.onCreate: $applicationContext")
    }

}
