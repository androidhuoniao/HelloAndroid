package com.gradle.rescompressor.compression

import com.gradle.rescompressor.command.Command
import java.io.File

/**
 * Represents a compression tool
 *
 * @author johnsonlee
 *
 * @param command The command for image compression
 */
abstract class CompressionTool(val command: Command) : CompressionTaskCreatorFactory {

}
