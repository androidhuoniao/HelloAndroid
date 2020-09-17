package com.grass.helloandroid

import android.app.KeyguardManager
import android.content.Context
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class TestMemoryLeakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var keyguardManager = this.getSystemService(KeyguardManager::class.java)
        Log.i("test", "SecondActivity.onCreate: $applicationContext")
//        ActivityListUtils.getInstance().addActivity(this)
//        var powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
        var systemService = getSystemService(PowerManager::class.java)
    }

}
