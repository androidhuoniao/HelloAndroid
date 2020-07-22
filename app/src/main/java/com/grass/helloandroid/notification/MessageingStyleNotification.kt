package com.grass.helloandroid.notification

import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.grass.helloandroid.R

/**
 *
 * Created by grassswwang
 * on 2020/7/22
 * Email: grassswwang@tencent.com
 */
object MessageingStyleNotification {

    fun showNotification(context: Context) {

        var message =
            NotificationCompat.MessagingStyle.Message("text", System.currentTimeMillis(), "sender")

        var notification = NotificationCompat.Builder(context, NotificationChannelFactory.CHANNELID)
            .apply {
                setSmallIcon(R.mipmap.ic_launcher)
                setStyle(
                    NotificationCompat.MessagingStyle("replyName")
                        .addMessage(message)
                )
            }
            .build()

        var nm = NotificationManagerCompat.from(context)
        nm.createNotificationChannel(NotificationChannelFactory.makeNotificationChannel())
        nm.notify(System.currentTimeMillis().toInt(), notification)
    }

}