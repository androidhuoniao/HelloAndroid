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
object InBoxStyleNotification {

    fun showNotification(context: Context) {
        var notification = NotificationCompat.Builder(context, NotificationChannelFactory.CHANNELID)
            .apply {
                setSmallIcon(R.mipmap.ic_launcher)
                setContentTitle("contentTitle:InboxStyle")
                setContentText("contextText:InboxStyle=============================================")
                setStyle(
                    NotificationCompat.InboxStyle()
                        .addLine("line1:xxxxxxxxxxxxxxxxxxxxxxx")
                        .addLine("line2:xxxxxxxxxxxxxxxxxxxxxxx")
                        .addLine("line3:xxxxxxxxxxxxxxxxxxxxxxx")
                        .addLine("line4:xxxxxxxxxxxxxxxxxxxxxxx")
                )
            }
            .build()

        var nm = NotificationManagerCompat.from(context)
        nm.createNotificationChannel(NotificationChannelFactory.makeNotificationChannel())
        nm.notify(System.currentTimeMillis().toInt(), notification)
    }
}