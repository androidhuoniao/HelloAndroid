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

    fun logMergeResourceTask(task: MergeResources) {

        task.doLast {

//                    var outputDir = mergeResources.outputDir
//                    var generatedPngsOutputDir = mergeResources.generatedPngsOutputDir
//                    var publicFile = mergeResources.publicFile
//                    var resourceDirsOutsideRootProjectDir =
//                        mergeResources.resourceDirsOutsideRootProjectDir
            var generatedDensities = task.generatedDensities
            LogUtil.log("mergeResources generatedDensities: $generatedDensities \n outputDir: ${task.outputDir}")
        }

    }

    fun logPackageResourceTask(task: Task) {
        task.doLast {
        }

    }

    fun logCompileResourceTask(task: Task) {
        LogUtil.log("-------------------------logCompileResourceTask---------------------------")
        task.inputs.files.forEach {
           LogUtil.log("logCompileResourceTask:inputs-$task ${it.path} ")
        }

        task.outputs.files.forEach {
            LogUtil.log("logCompileResourceTask:outPuts-$task ${it.path} ")
        }
        LogUtil.log("-------------------------logCompileResourceTask end---------------------------")
    }
}