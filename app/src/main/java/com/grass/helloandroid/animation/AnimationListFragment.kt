package com.grass.helloandroid.animation

import com.airbnb.epoxy.EpoxyController
import com.grass.helloandroid.fragment.BaseEpoxyFragment
import com.grass.helloandroid.views.marquee
import com.grass.helloandroid.views.previewItemView

/**
 *
 * Created by grassswwang
 * on 2020/7/25
 * Email: grassswwang@tencent.com
 */
class AnimationListFragment : BaseEpoxyFragment() {
    override fun EpoxyController.buildModels() {
        marquee {
            title("animation list")
            id("animation list")
        }

        previewItemView {
            id("rotate")
            title("rotate")
            clickListener { _ ->

            }
        }
    }
}