package com.grass.helloandroid

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Trace
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.grass.helloandroid.aop.AopOnclick
import com.grass.helloandroid.fragment.AndroidFragment
import com.grass.helloandroid.fragment.KotlinFragment
import com.grass.helloandroid.fragment.NotificationFragment
import com.grass.helloandroid.fragment.RxJavaFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    IFragmentSwitcher {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        savedInstanceState ?: showFragment(NotificationFragment())
        Trace.beginSection("grass_test_group")
        testGroup()
        Trace.endSection()

        var application = application
        var applicationContext = applicationContext
        Log.i("test", "onCreate: appliciaton: $application, applicationContext: $applicationContext")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.rxjava -> showFragment(RxJavaFragment())
            R.id.notification -> showFragment(NotificationFragment())
            R.id.kotlin -> showFragment(KotlinFragment())
            R.id.androiddemo -> showFragment(AndroidFragment())
            else -> return false
        }
        return true
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content, fragment)
            .commit()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }


    override fun showFragment(action: Int, fragment: Fragment) {
        when (action) {
            FragmentActionConsts.ACTION_ADD -> {
                supportFragmentManager.beginTransaction().add(R.id.content, fragment)
                    .commit()
            }
            FragmentActionConsts.ACTION_REPLACE -> {
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment)
                    .commit()
            }
        }
    }

    fun testGroup() {
        test1()
        test2()
        test3()
    }

    fun test1() {
        BitmapFactory.decodeResource(resources, R.drawable.drawable_shadow_popup_bg);
    }

    fun test2() {
        Log.i("grass", "test2: is working")
    }

    fun test3() {
        Log.i("grass", "test3: is working")
    }
}