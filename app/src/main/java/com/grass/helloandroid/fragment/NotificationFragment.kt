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
import com.grass.helloandroid.views.marquee
import com.grass.helloandroid.views.previewItemView


/**
 *
 * Created by grassswwang
 * on 2020/7/10
 * Email: grassswwang@tencent.com
 */
class NotificationFragment : BaseEpoxyFragment() {
    var CALENDAR_ID: String? = "channel_01"

    override fun EpoxyController.buildModels() {
        marquee {
            id("notificatioo")
            title("通知样式")
        }

        previewItemView {
            id("bigtextstyle")
            title("BigTextStyle")
            clickListener { _ ->
                showNotification(activity!!)
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

    private fun showNotification(context: Context) {
        var notificationManager = NotificationManagerCompat.from(activity!!)
        val notification: Notification
        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(makeNotificationChannel())
        }
        builder = NotificationCompat.Builder(context, CALENDAR_ID!!)
        val pendingIntent = PendingIntent.getActivity(
            context, 11,
            Intent(context, SecondActivity::class.java), 0
        )
        notification = builder.setSmallIcon(R.mipmap.sym_def_app_icon)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
                // 显示通知右侧的icon
            .setLargeIcon(
            BitmapFactory.decodeResource(
                context.getResources(),
                R.mipmap.sym_def_app_icon
            )
        )
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .setBigContentTitle("i am bigTextStyle notification")
                    .bigText(
                        "无论是否意识到 Gradle 的存在，每位 Android 程序员都会直接或间接的与 Gradle 打交道。每当通过 Android Studio " +
                                "新建一个工程时，AS 都会自动创建一个通用的目录结构，然后就可以进行开发，" +
                                "在 app 的 build.gradle 中添加一些依赖，点击右上角的 Sync Now"
                    )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun makeNotificationChannel(): NotificationChannel {
        val notificationChannel = NotificationChannel(
            CALENDAR_ID, "123",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        // 既可以使用apply，也可以使用with
        notificationChannel.apply {
            // 设置渠道描述
            description = "测试通知组"
            // 是否绕过请勿打扰模式
            canBypassDnd()
            // 设置绕过请勿打扰模式
            setBypassDnd(true)
            // 桌面Launcher的消息角标
            canShowBadge()
            // 设置显示桌面Launcher的消息角标
            setShowBadge(true)
            // 设置通知出现时声音，默认通知是有声音的
            setSound(null, null)
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            enableLights(true)
            lightColor = Color.RED
            // 设置通知出现时的震动（如果 android 设备支持的话）
            enableVibration(true)
            vibrationPattern = longArrayOf(
                100, 200, 300, 400, 500, 400,
                300, 200, 400
            )
        }
        return notificationChannel;
    }
}