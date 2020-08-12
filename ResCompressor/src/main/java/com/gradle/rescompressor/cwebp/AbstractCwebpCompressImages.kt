package com.gradle.rescompressor.task.compression.cwebp

import com.gradle.rescompressor.compression.CompressionOptions
import com.gradle.rescompressor.compression.task.CompressImages
import com.gradle.rescompressor.gradle.getProperty
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * Represents an abstraction of cwebp compression task
 *
 * @author johnsonlee
 */
abstract class AbstractCwebpCompressImages : CompressImages<CompressionOptions>() {

    @TaskAction
    fun run() {
        this.options = CompressionOptions(project.getProperty(PROPERTY_OPTION_QUALITY, 80))
        compress(File::hasNotAlpha)
    }

    protected abstract fun compress(filter: (File) -> Boolean)

}