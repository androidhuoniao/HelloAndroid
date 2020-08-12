package com.gradle.rescompressor.compression

import com.gradle.rescompressor.compression.CompressionTaskCreator

/**
 * Represents a factory of [CompressionTaskCreator]
 *
 * @author johnsonlee
 */
interface CompressionTaskCreatorFactory {

    fun newCompressionTaskCreator(): CompressionTaskCreator

}

