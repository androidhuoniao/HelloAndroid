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
object BigTextStyleNotification {

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
        notification = builder.setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("contentTitle: bigTextStyle notification")
            .setContentText("contentText: Much longer text that cannot fit one line...")
            // 显示通知右侧的icon
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.getResources(), R.mipmap.largeicon
                )
            )
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .setBigContentTitle("BigContentTitle: i am bigTextStyle notification")
                    .bigText(
                        "bigText: 无论是否意识到 Gradle 的存在，每位 Android 程序员都会直接或间接的与 Gradle 打交道。每当通过 Android Studio " +
                                "新建一个工程时，AS 都会自动创建一个通用的目录结构，然后就可以进行开发，" +
                                "在 app 的 build.gradle 中添加一些依赖，点击右上角的 Sync Now"
                    )
                    .setSummaryText("setSummaryText: 11111111111111111111111")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}