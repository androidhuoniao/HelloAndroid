package com.grass.helloandroid

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.grass.helloandroid.fragment.KeepAliveSampleFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        savedInstanceState ?: showFragment(KeepAliveSampleFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.keepalive -> showFragment(KeepAliveSampleFragment())
//            R.id.other -> showFragment(OtherSampleFragment())
//            else -> return false
//        }
        return true
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.content, fragment)
            .commit()
    }
}