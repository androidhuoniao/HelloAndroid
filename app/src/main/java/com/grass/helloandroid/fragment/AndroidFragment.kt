package com.grass.helloandroid.fragment

import android.content.Intent
import com.airbnb.epoxy.EpoxyController
import com.grass.helloandroid.FragmentActionConsts
import com.grass.helloandroid.TestMemoryLeakActivity
import com.grass.helloandroid.TouchDemoActivity
import com.grass.helloandroid.accountsync.AccountCreator
import com.grass.helloandroid.animation.AnimationListFragment
import com.grass.helloandroid.assets.AssetsDemo
import com.grass.helloandroid.looper.HelloLooper
import com.grass.helloandroid.span.SpanActivity
import com.grass.helloandroid.suanfa.SuanMain
import com.grass.helloandroid.views.marquee
import com.grass.helloandroid.views.previewItemView
import com.grass.helloandroid.webview.WebviewActivity
import java.lang.Exception
import kotlin.reflect.KClass

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
                getFragmentSwitcher().showFragment(
                    FragmentActionConsts.ACTION_ADD,
                    AnimationListFragment()
                )
            }
        }

        previewItemView {
            id("account_sync")
            title("account sync 保活")
            clickListener { _ ->
                AccountCreator.createSyncAccount(activity!!)
            }
        }

        previewItemView {
            id("load_local_html")
            title("加载本地url")
            clickListener { _ ->
//                var intent: Intent = Intent(activity, WebviewActivity::class.java)
//                activity!!.startActivity(intent)
                startNextActivity(WebviewActivity::class)
            }
        }

        previewItemView {
            id("assets")
            title("assets demo")
            clickListener { _ ->
                AssetsDemo().test(activity)
            }
        }

        previewItemView {
            id("looperDemo")
            title("looper demo")
            clickListener { _ ->
                HelloLooper.startLooper(activity!!)
            }
        }
        previewItemView {
            id("suanfa")
            title("SuanFa demo")
            clickListener { _ ->
                SuanMain.test()
            }
        }

        previewItemView {
            id("memoryLeakDemo")
            title("memoryLeakDemo")
            clickListener { _ ->
                startNextActivity(TestMemoryLeakActivity::class)
            }
        }

        previewItemView {
            id("TouchEvent")
            title("TouchEventDemo")
            clickListener { _ ->
                startNextActivity(TouchDemoActivity::class)
            }
        }

        previewItemView {
            id("MeasureDemo")
            title("MeasureDemo")
            clickListener { _ ->
                startNextActivity(TouchDemoActivity::class)
            }
        }
    }

    fun <T : Any> startNextActivity(clazz: KClass<T>) {
        var intent: Intent = Intent(activity, clazz.java)
        activity!!.startActivity(intent)
    }
}