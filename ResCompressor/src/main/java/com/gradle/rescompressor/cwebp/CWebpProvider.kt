package com.gradle.rescompressor.task.compression.cwebp

import com.google.auto.service.AutoService
import com.gradle.rescompressor.command.Command
import com.gradle.rescompressor.command.CommandProvider

/**
 * Represents cwebp command provider
 *
 * @author johnsonlee
 */
@AutoService(CommandProvider::class)
class CWebpProvider : CommandProvider {

    override fun get(): Collection<Command> = listOf(Command(CWEBP, javaClass.classLoader.getResource(PREBUILT_CWEBP_EXECUTABLE)!!))

}