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
object BigPictureStyleNotification {

    fun showNotification(context: Context) {
        var notification = NotificationCompat.Builder(context, NotificationChannelFactory.CHANNELID)
            .apply {
                setSmallIcon(R.mipmap.ic_launcher)
                setContentTitle("contentTitle:bigPictureSytleNotification")
                setContentText("contextText:bigPictureStylebigPictureStylebigPictureStylebigPictureStylebigPictureStyle")
                setStyle(
                    NotificationCompat.BigPictureStyle()
                        .setSummaryText("summeryText")
                        .setBigContentTitle("setBigContentTitle")
                        .bigLargeIcon(
                            BitmapFactory.decodeResource(
                                context.resources,
                                R.mipmap.largeicon
                            )
                        )
                        .bigPicture(
                            BitmapFactory.decodeResource(
                                context.resources,
                                R.mipmap.bigpicture
                            )
                        )
                )
            }
            .build()

        var nm = NotificationManagerCompat.from(context)
        nm.createNotificationChannel(NotificationChannelFactory.makeNotificationChannel())
        nm.notify(System.currentTimeMillis().toInt(), notification)
    }

    fun showNotification2(context: Context) {
        var notification = NotificationCompat.Builder(context, NotificationChannelFactory.CHANNELID)
            .apply {
                setSmallIcon(R.mipmap.ic_launcher)
                setContentTitle("contentTitle:bigPictureSytleNotification")
                setContentText("contextText:bigPictureStylebigPictureStylebigPictureStylebigPictureStylebigPictureStyle")
                    .setLargeIcon(
                        BitmapFactory.decodeResource(
                            context.resources,
                            R.mipmap.largeicon
                        )
                    )
                setStyle(
                    NotificationCompat.BigPictureStyle()
                        .setSummaryText("summeryText")
                        .setBigContentTitle("setBigContentTitle")
                        .bigPicture(
                            BitmapFactory.decodeResource(
                                context.resources,
                                R.mipmap.bigpicture
                            )
                        )
                        .bigLargeIcon(null)
                )
            }
            .build()

        var nm = NotificationManagerCompat.from(context)
        nm.createNotificationChannel(NotificationChannelFactory.makeNotificationChannel())
        nm.notify(System.currentTimeMillis().toInt(), notification)
    }
}