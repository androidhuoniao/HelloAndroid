package com.smallsoho.mcplugin.image

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.api.BaseVariantImpl
import com.android.build.gradle.tasks.MergeResources
import com.smallsoho.mcplugin.image.utils.LogUtil
import org.gradle.api.Project
import org.gradle.api.Task

/**
 *
 * Created by grassswwang
 * on 2020/8/6
 * Email: grassswwang@tencent.com
 */
object TaskLogger {
    private const val TAG = "TaskLogger"

    fun logInputs(tag: String, proj: Project) {
        proj.tasks.forEach { task ->
            task.doFirst {
                LogUtil.log("taskInfo--${task.toString()} ${task::class.java.simpleName}")
                task.inputs.files.forEach {
                    LogUtil.log("$tag--logInputs: $task ${it.path}")
                }
            }
        }
    }

    fun logAllProjects( proj: Project) {

        LogUtil.log("----------logAllProjects begin--------------")
        proj.allprojects {
           LogUtil.log("logAllProjects: ${it.name}")
        }
        LogUtil.log("----------logAllProjects end--------------")
    }

    fun logOutputs(tag: String, proj: Project) {
        proj.tasks.forEach { task ->
            task.doLast {
                task.inputs.files.forEach {
                    LogUtil.log("$tag--logInputs: $task ${it.path}")
                }
                task.outputs.files.forEach {
                    LogUtil.log("$tag--logOutputs: $task ${it.path}")
                }
            }
        }
    }

    fun logAllTasks(proj: Project) {
        proj.tasks.forEach {
            LogUtil.log("logAllTasks--${it.name} $it")
        }
    }

    fun watchTask(tag: String, taskName: String, proj: Project) {
        proj.tasks.findByName(taskName)?.doFirst {
            LogUtil.log("watchTask: $tag.doFirst")

        }?.doLast {
            LogUtil.log("watchTask: $tag.doLast")
        }
    }

    fun log(tag: String, proj: Project) {

    }

    fun log(tag: String, varint: BaseVariantImpl) {

    }

    fun log(tag: String, extension: AppExtension) {

    }

    fun log(tag: String, extension: LibraryExtension) {

    }

    fun logMergeDebugResourceTask(task: MergeResources) {
        LogUtil.log("-------------------------logMergeDebugResourceTask $task---------------------------")

        task.inputs.sourceFiles.forEach {
            LogUtil.log("logMergeDebugResourceTask:sourceFiles-${it.path} ")
        }
        task.inputs.files.forEach {
            LogUtil.log("logMergeDebugResourceTask:inputs- ${it.path} ")
        }

        task.inputs.properties.forEach {
            LogUtil.log("logMergeDebugResourceTask:properties- ${it} ")
        }

        task.outputs.files.forEach {
            LogUtil.log("logMergeDebugResourceTask:outPuts- ${it.path} ")
        }
        LogUtil.log("-------------------------logMergeDebugResourceTask end---------------------------")
    }


    fun logCompileResourceTask(task: Task) {
        LogUtil.log("-------------------------logCompileResourceTask $task---------------------------")

        task.inputs.sourceFiles.forEach {
            LogUtil.log("logCompileResourceTask:sourceFiles-${it.path} ")
        }
        task.inputs.files.forEach {
           LogUtil.log("logCompileResourceTask:inputs- ${it.path} ")
        }

        task.inputs.properties.forEach {
            LogUtil.log("logCompileResourceTask:properties- ${it} ")
        }

        task.outputs.files.forEach {
            LogUtil.log("logCompileResourceTask:outPuts- ${it.path} ")
        }
        LogUtil.log("-------------------------logCompileResourceTask end---------------------------")
    }
}