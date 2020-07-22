package com.grass.helloandroid.fragment

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.airbnb.epoxy.EpoxyController
import com.grass.helloandroid.SecondActivity
import com.grass.helloandroid.notification.*
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
            id("notificatioo")
            title("通知样式")
        }

        previewItemView {
            id("bigtextstyle")
            title("BigTextStyle")
            clickListener { _ ->
                BigTextStyleNotification.showNotification(activity!!)
            }
        }

        previewItemView {
            id("bigPictureStyle")
            title("bigPictureStyle")
            clickListener { _ ->
                BigPictureStyleNotification.showNotification(activity!!)
                BigPictureStyleNotification.showNotification2(activity!!)
            }
        }

        previewItemView {
            id("MessagingStyle ")
            title("MessagingStyle ")
            clickListener { _ ->
                MessageingStyleNotification.showNotification(activity!!)
            }
        }

        previewItemView {
            id("inboxStyle")
            title("inboxStyle")
            clickListener { _ ->
                InBoxStyleNotification.showNotification(activity!!)
            }
        }
        previewItemView {
            id("lockscreen")
            title("lockscreen")
            clickListener { _ ->
                LockScreenNotification.showNotification(activity!!)
            }
        }
        previewItemView {
            id("headsup")
            title("headsup")
            clickListener { _ ->
                HeadsupNotification.showNotification(activity!!)
            }
        }
        previewItemView {
            id("mediacontroller")
            title("mediacontroller")
            clickListener { _ ->
                MediaStyleNotification.showNotification(activity!!)
            }
        }
    }

}