package com.gradle.rescompressor.compression

import com.gradle.rescompressor.gradle.mergeResourcesTask
import com.gradle.rescompressor.gradle.processResTask
import com.gradle.rescompressor.gradle.project
import com.android.build.gradle.api.BaseVariant
import com.gradle.rescompressor.command.CommandInstaller
import com.gradle.rescompressor.compression.task.CompressImages
import com.gradle.rescompressor.gradle.aapt2Enabled
import com.gradle.rescompressor.utils.LogUtil
import org.gradle.api.Task
import java.io.File
import kotlin.reflect.KClass

/**
 * Represents a simple implementation of [CompressionTaskCreator]
 *
 * @author johnsonlee
 */
class SimpleCompressionTaskCreator(private val tool: CompressionTool, private val compressor: (Boolean) -> KClass<out CompressImages<out CompressionOptions>>) : CompressionTaskCreator {

    override fun getCompressionTaskClass(aapt2: Boolean) = compressor(aapt2)

    override fun createCompressionTask(variant: BaseVariant, results: CompressionResults, name: String, supplier: () -> Collection<File>, vararg deps: Task): CompressImages<out CompressionOptions> {
        val aapt2 = variant.project.aapt2Enabled
        val install = getCommandInstaller(variant)
        install.doLast {
            LogUtil.log("${variant.name} CommandInstaller.doFirst is working")
        }

        LogUtil.log("${variant.name} createCompressionTask is working aapt2: "+aapt2)
        return variant.project.tasks.create("compress${variant.name.capitalize()}${name.capitalize()}With${tool.command.name.substringBefore('.').capitalize()}", getCompressionTaskClass(aapt2).java) { task ->
            task.tool = tool
            task.variant = variant
            task.results = results
            task.supplier = {
                supplier.invoke().filter { it.length() > 0 }.sortedBy { it }
            }
            task.inputs.property("time",System.currentTimeMillis())
        }.apply {
            dependsOn(install, deps)
            if(deps.size > 0){
                LogUtil.log("${variant.name} createCompressionTask deps: ${deps.get(0)}")
                deps[0].doLast {
                    LogUtil.log("${variant.name} $it.doLast is working")
                }
            }
            LogUtil.log("${variant.name} createCompressionTask dependsOn ")
            variant.processResTask.dependsOn(this)
            variant.processResTask.doFirst {
                LogUtil.log("${variant.name} createCompressionTask processResTask.doFirst is working")
            }
        }
    }

    private fun getCommandInstaller(variant: BaseVariant): Task {
        LogUtil.log("${variant.name} getCommandInstaller")
        val name = "install${tool.command.name.substringBefore('.').capitalize()}"
        return variant.project.tasks.findByName(name) ?: variant.project.tasks.create(name, CommandInstaller::class.java) {
            it.command = tool.command
        }.dependsOn(variant.mergeResourcesTask)
    }

}

