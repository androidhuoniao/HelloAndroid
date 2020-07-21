package com.grass.helloandroid.fragment

import com.airbnb.epoxy.EpoxyController
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
            id("other")
            title("other")
        }

        previewItemView {
            id("test")
            title("test")
            clickListener { _ ->

            }
        }
    }
}