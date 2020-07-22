package com.grass.helloandroid.notification

import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.grass.helloandroid.R
//import android.support.v4.media.app.NotificationCompat as MediaNotificationCompat

/**
 *
 * Created by grassswwang
 * on 2020/7/22
 * Email: grassswwang@tencent.com
 * 参考示例：https://developer.android.com/training/notify-user/expanded?hl=en
 */
object MediaStyleNotification {

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

}