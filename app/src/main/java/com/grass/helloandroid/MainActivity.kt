package com.grass.helloandroid

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.grass.helloandroid.fragment.AndroidFragment
import com.grass.helloandroid.fragment.KotlinFragment
import com.grass.helloandroid.fragment.NotificationFragment
import com.grass.helloandroid.fragment.RxJavaFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        savedInstanceState ?: showFragment(NotificationFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.rxjava -> showFragment(RxJavaFragment())
            R.id.notification -> showFragment(NotificationFragment())
//            R.id.kotlin -> showFragment(KotlinFragment())
            R.id.androiddemo -> showFragment(AndroidFragment())
            else -> return false
        }
        return true
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content, fragment)
            .commit()
    }
}