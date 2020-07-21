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
class NotificationFragment : BaseEpoxyFragment() {

    override fun EpoxyController.buildModels() {
        marquee {
            id("keep live")
            title("保活")
        }

        previewItemView {
            id("start_alarm")
            title("start alarm")
            clickListener { _ ->

            }
        }

        previewItemView {
            id("SyncWaker")
            title("SyncWaker")
            clickListener { _ ->
            }
        }

        previewItemView {
            id("ReceiversWaker")
            title("ReceiversWaker")
            clickListener { _ ->
            }
        }
    }
}