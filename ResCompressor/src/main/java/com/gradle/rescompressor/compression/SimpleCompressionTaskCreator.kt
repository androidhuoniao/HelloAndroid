package com.gradle.rescompressor.compression

import com.gradle.rescompressor.gradle.mergeResourcesTask
import com.gradle.rescompressor.gradle.processResTask
import com.gradle.rescompressor.gradle.project
import com.android.build.gradle.api.BaseVariant
import com.gradle.rescompressor.command.CommandInstaller
import com.gradle.rescompressor.compression.task.CompressImages
import com.gradle.rescompressor.gradle.aapt2Enabled
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

        return variant.project.tasks.create("compress${variant.name.capitalize()}${name.capitalize()}With${tool.command.name.substringBefore('.').capitalize()}", getCompressionTaskClass(aapt2).java) { task ->
            task.tool = tool
            task.variant = variant
            task.results = results
            task.supplier = {
                supplier.invoke().filter { it.length() > 0 }.sortedBy { it }
            }
        }.apply {
            dependsOn(install, deps)
            variant.processResTask.dependsOn(this)
        }
    }

    private fun getCommandInstaller(variant: BaseVariant): Task {
        val name = "install${tool.command.name.substringBefore('.').capitalize()}"
        return variant.project.tasks.findByName(name) ?: variant.project.tasks.create(name, CommandInstaller::class.java) {
            it.command = tool.command
        }.dependsOn(variant.mergeResourcesTask)
    }

}

