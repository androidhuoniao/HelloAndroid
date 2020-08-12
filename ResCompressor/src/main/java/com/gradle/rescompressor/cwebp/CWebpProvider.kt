package com.gradle.rescompressor.task.compression.cwebp

import com.gradle.rescompressor.command.Command
import com.gradle.rescompressor.command.CommandProvider

/**
 * Represents cwebp command provider
 *
 * @author johnsonlee
 */
class CWebpProvider : CommandProvider {

    override fun get(): Collection<Command> = listOf(Command(CWEBP, javaClass.classLoader.getResource(PREBUILT_CWEBP_EXECUTABLE)!!))

}