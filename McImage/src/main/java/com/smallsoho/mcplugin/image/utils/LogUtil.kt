package com.smallsoho.mcplugin.image.utils

import java.io.File

class LogUtil {

    companion object {

        fun log(stage: String, filePath: String, oldInfo: String, newInfo: String) {
            println("[$stage][$filePath][oldInfo: $oldInfo][newInfo: $newInfo]")
        }

        fun log(stage: String, info: String, result: String) {
            println("[$stage][Info: $info][Result: $result]")
        }

        fun log(str: String) {
            println(str)
        }

        fun log(exception: Exception) {
            println(exception)
        }

        fun log(tag: String, files: Collection<File>) {
            log("---------------$tag---------------")
            files.forEach {
                LogUtil.log("   "+it.path)
            }
            log("---------------$tag end---------------")
        }
    }

}
