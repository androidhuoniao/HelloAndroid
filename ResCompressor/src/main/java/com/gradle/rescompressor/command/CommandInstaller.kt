package com.gradle.rescompressor.command

import com.gradle.rescompressor.kotlinx.OS
import com.gradle.rescompressor.kotlinx.file
import com.gradle.rescompressor.utils.LogUtil
import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * Represents a task for command installation
 *
 * @author johnsonlee
 */
@CacheableTask
open class CommandInstaller : DefaultTask() {

    @get:Input
    lateinit var command: Command

    @get:OutputFile
    val location: File
        get() = project.buildDir.file("bin", command.name)

    @TaskAction
    fun install() {
        logger.info("Installing $command => $location")
        LogUtil.log("Installing $command => $location")
        this.command.location.openStream().buffered().use { input ->
            FileUtils.copyInputStreamToFile(input, location)
            project.exec {
                it.commandLine = when {
                    OS.isLinux() || OS.isMac() -> listOf("chmod", "+x", location.absolutePath)
                    OS.isWindows() -> listOf("cmd", "/c echo Y|cacls ${location.absolutePath} /t /p everyone:f")
                    else -> TODO("Unsupported OS ${OS.name}")
                }
            }
        }
    }

}
