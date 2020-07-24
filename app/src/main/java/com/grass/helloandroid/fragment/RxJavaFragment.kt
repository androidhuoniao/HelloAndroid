package com.grass.helloandroid.fragment

import android.content.Intent
import com.airbnb.epoxy.EpoxyController
import com.grass.helloandroid.SecondActivity
import com.grass.helloandroid.views.marquee
import com.grass.helloandroid.views.previewItemView

/**
 *
 * Created by grassswwang
 * on 2020/7/10
 * Email: grassswwang@tencent.com
 */
class RxJavaFragment : BaseEpoxyFragment() {

    override fun EpoxyController.buildModels() {
        marquee {
            id("RxJava2")
            title("RxJava2")
        }

        previewItemView {
            id("groupBy")
            title("groupBy")
            clickListener { _ ->
                startSecond()
            }
        }
    }

    private fun startSecond() {
        var intent: Intent = Intent(activity, SecondActivity::class.java)
        activity!!.startActivity(intent)
    }
}