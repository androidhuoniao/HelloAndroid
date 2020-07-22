package com.grass.helloandroid.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

/**
 *
 * Created by grassswwang
 * on 2020/7/22
 * Email: grassswwang@tencent.com
 */
object NotificationChannelFactory {

    var CHANNELID = "10000000000000"

    @RequiresApi(Build.VERSION_CODES.O)
    fun makeNotificationChannel(): NotificationChannel {
        val notificationChannel = NotificationChannel(
            CHANNELID, "channelname：123",
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