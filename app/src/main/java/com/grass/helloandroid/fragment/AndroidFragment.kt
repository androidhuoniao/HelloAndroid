package com.grass.helloandroid.fragment

import android.content.Intent
import com.airbnb.epoxy.EpoxyController
import com.grass.helloandroid.FragmentActionConsts
import com.grass.helloandroid.accountsync.AccountCreator
import com.grass.helloandroid.animation.AnimationListFragment
import com.grass.helloandroid.span.SpanActivity
import com.grass.helloandroid.views.marquee
import com.grass.helloandroid.views.previewItemView

/**
 *
 * Created by grassswwang
 * on 2020/7/10
 * Email: grassswwang@tencent.com
 */
class AndroidFragment : BaseEpoxyFragment() {

    override fun EpoxyController.buildModels() {
        marquee {
            id("Android")
            title("Android")
        }

        previewItemView {
            id("span")
            title("span")
            clickListener { _ ->
                var intent: Intent = Intent(activity, SpanActivity::class.java)
                activity!!.startActivity(intent)
            }
        }

        previewItemView {
            id("animation")
            title("animation")
            clickListener { _ ->
                getFragmentSwitcher().showFragment(FragmentActionConsts.ACTION_ADD, AnimationListFragment())
            }
        }

        previewItemView {
            id("account_sync")
            title("account sync 保活")
            clickListener { _ ->
                AccountCreator.createSyncAccount(activity!!)
            }
        }
    }
}