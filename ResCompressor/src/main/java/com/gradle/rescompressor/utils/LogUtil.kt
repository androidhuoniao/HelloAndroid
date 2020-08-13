package com.gradle.rescompressor.utils

import org.gradle.api.Task
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
                LogUtil.log("   " + it.path)
            }
            log("---------------$tag end---------------")
        }

        /**
         * 打印Task的inputs，outputs和properties
         */
        fun logTaskInfo(tag: String, task: Task) {
            val methodName = "$tag logTaskInfo"
            LogUtil.log("-------------------------$methodName $task---------------------------")

            task.inputs.sourceFiles.forEach {
                LogUtil.log("$methodName:sourceFiles-${it.path} ")
            }
            task.inputs.files.forEach {
                LogUtil.log("$methodName:inputs- ${it.path} ")
            }

            task.inputs.properties.forEach {
                LogUtil.log("$methodName:properties- ${it} ")
            }

            task.outputs.files.forEach {
                LogUtil.log("$methodName:outPuts- ${it.path} ")
            }
            LogUtil.log("-------------------------$methodName end---------------------------")
        }
    }

}
