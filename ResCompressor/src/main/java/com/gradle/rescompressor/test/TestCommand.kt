package com.gradle.rescompressor.test

import com.gradle.rescompressor.command.CommandService
import com.gradle.rescompressor.task.compression.cwebp.CWEBP
import com.gradle.rescompressor.task.compression.cwebp.CWebpProvider
import com.gradle.rescompressor.task.compression.cwebp.PREBUILT_CWEBP_EXECUTABLE
import com.gradle.rescompressor.utils.LogUtil

/**
 *
 * Created by grassswwang
 * on 2020/8/14
 * Email: grassswwang@tencent.com
 */
object TestCommand {
    fun testCWebpProvider() {
        var webpProvider = CWebpProvider()
        var webpCommand = CommandService.get(CWEBP)
        var classLoader = javaClass.classLoader
        var resource = javaClass.classLoader.getResource(PREBUILT_CWEBP_EXECUTABLE)
        LogUtil.log(
            "testCWebpProvider==========\n" +
                    "commandsSize:  ${CommandService.getCommandsSize()} \n" +
                    "webpCommand: ${webpCommand?.location} \n" +
                    "classLoader: $classLoader \n" +
                    "PREBUILT_CWEBP_EXECUTABLE: $PREBUILT_CWEBP_EXECUTABLE \n" +
                    "resource: $resource \n" +
                    ""
        )
    }
}