package com.grass.helloandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.grass.helloandroid.touchevent.TestGroupFlag
import kotlinx.android.synthetic.main.activity_layout_touch_demo.*


class TouchDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_touch_demo)
        listview.adapter = ListAdapter()
        TestGroupFlag.test()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }
    class ListAdapter() : BaseAdapter() {
        val data = mutableListOf<String>()

        init {
            for (index in 0..10) {
                data.add(index.toString())
            }
        }

        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int): Any {
            return data.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var resultView =
                LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)
            resultView.findViewById<TextView>(R.id.content_tv).setText(getItem(position).toString())
            resultView.setOnClickListener {
                Toast.makeText(parent?.context, getItem(position).toString(), Toast.LENGTH_SHORT).show()
            }
            return resultView
        }
    }


}
