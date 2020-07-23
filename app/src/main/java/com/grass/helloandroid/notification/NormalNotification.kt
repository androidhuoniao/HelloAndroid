package com.grass.helloandroid.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.grass.helloandroid.R
import com.grass.helloandroid.SecondActivity

/**
 *
 * Created by grassswwang
 * on 2020/7/22
 * Email: grassswwang@tencent.com
 */
object NormalNotification {

    fun showNotification(context: Context) {
        var notificationManager = NotificationManagerCompat.from(context)
        val notification: Notification
        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(NotificationChannelFactory.makeNotificationChannel())
        }
        builder = NotificationCompat.Builder(context, NotificationChannelFactory.CHANNELID)
        val pendingIntent = PendingIntent.getActivity(
            context, 11,
            Intent(context, SecondActivity::class.java), 0
        )
        notification = builder
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("contentTitle: ")
            .setContentText("contentText: ")
            // 显示通知右侧的icon
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.getResources(), R.mipmap.largeicon
                )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}