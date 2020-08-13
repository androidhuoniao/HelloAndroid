package com.gradle.rescompressor.task.compression.cwebp

import com.android.SdkConstants
import com.android.SdkConstants.FD_RES
import com.android.builder.model.AndroidProject.FD_INTERMEDIATES
import com.android.sdklib.BuildToolInfo
import com.gradle.rescompressor.aapt2.Aapt2Container
import com.gradle.rescompressor.aapt2.metadata
import com.gradle.rescompressor.compression.CompressionResult
import com.gradle.rescompressor.compression.task.Aapt2ActionData
import com.gradle.rescompressor.gradle.buildTools
import com.gradle.rescompressor.gradle.mergedManifests
import com.gradle.rescompressor.gradle.project
import com.gradle.rescompressor.gradle.scope
import com.gradle.rescompressor.kotlinx.CSI_RED
import com.gradle.rescompressor.kotlinx.CSI_RESET
import com.gradle.rescompressor.kotlinx.file
import com.gradle.rescompressor.kotlinx.search
import com.gradle.rescompressor.utils.LogUtil
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.OutputDirectory
import java.io.File
import java.util.stream.Collectors
import javax.xml.parsers.SAXParserFactory

/**
 * Represents a task for compiled image compression using cwebp
 *
 * @author johnsonlee
 */
@CacheableTask
internal open class CwebpCompressFlatImages : AbstractCwebpCompressImages() {

    @get:OutputDirectory
    private val compressedRes: File
        get() = variant.project.buildDir.file(FD_INTERMEDIATES).file("compressed_${FD_RES}_cwebp", variant.dirName, this.name)

    override fun compress(filter: (File) -> Boolean) {
        LogUtil.log("CwebpCompressFlatImages.compress is working")
        val indent= "   "
        val cwebp = this.compressor.canonicalPath
        val aapt2 = variant.scope.buildTools.getPath(BuildToolInfo.PathId.AAPT2)
        LogUtil.log("$indent cwebp: $cwebp")
        LogUtil.log("$indent aapt2: $aapt2")
        val parser = SAXParserFactory.newInstance().newSAXParser()
        val icons = variant.scope.mergedManifests.search {
            it.name == SdkConstants.ANDROID_MANIFEST_XML
        }.parallelStream().map { manifest ->
            LauncherIconHandler().let {
                parser.parse(manifest, it)
                it.icons
            }
        }.flatMap {
            it.parallelStream()
        }.collect(Collectors.toSet())

        LogUtil.log("$indent icons: $icons")
        // Google Play only accept APK with PNG format launcher icon
        // https://developer.android.com/topic/performance/reduce-apk-size#use-webp
        val isNotLauncherIcon: (File, Aapt2Container.Metadata) -> Boolean = { input, metadata ->
            if (!icons.contains(metadata.resourceName)) true else false.also {
                val s0 = input.length()
                results.add(CompressionResult(input, s0, s0, File(metadata.sourcePath)))
            }
        }

        LogUtil.log("$indent images: ${images.size}")
        images.parallelStream().map {
            it to it.metadata
        }.filter {
            isNotLauncherIcon(it.first, it.second)
        }.filter {
            filter(File(it.second.sourcePath))
        }.map {
            val output = compressedRes.file("${it.second.resourcePath.substringBeforeLast('.')}.webp")
            Aapt2ActionData(it.first, it.second, output,
                    listOf(cwebp, "-mt", "-quiet", "-q", options.quality.toString(), it.second.sourcePath, "-o", output.absolutePath),
                    listOf(aapt2, "compile", "-o", it.first.parent, output.absolutePath))
        }.forEach {
            it.output.parentFile.mkdirs()
            val s0 = File(it.metadata.sourcePath).length()
            val rc = project.exec { spec ->
                spec.isIgnoreExitValue = true
                spec.commandLine = it.cmdline
            }
            LogUtil.log("result: ${rc.exitValue} path: "+it.input.path)
            when (rc.exitValue) {
                0 -> {
                    val s1 = it.output.length()
                    if (s1 > s0) {
                        results.add(CompressionResult(it.input, s0, s0, File(it.metadata.sourcePath)))
                        it.output.delete()
                    } else {
                        val rcAapt2 = project.exec { spec ->
                            spec.isIgnoreExitValue = true
                            spec.commandLine = it.aapt2
                        }
                        if (0 == rcAapt2.exitValue) {
                            results.add(CompressionResult(it.input, s0, s1, File(it.metadata.sourcePath)))
                            it.input.delete()
                        } else {
                            logger.error("${CSI_RED}Command `${it.aapt2.joinToString(" ")}` exited with non-zero value ${rc.exitValue}$CSI_RESET")
                            results.add(CompressionResult(it.input, s0, s0, File(it.metadata.sourcePath)))
                            rcAapt2.assertNormalExitValue()
                        }
                    }
                }
                else -> {
                    logger.error("${CSI_RED}Command `${it.cmdline.joinToString(" ")}` exited with non-zero value ${rc.exitValue}$CSI_RESET")
                    results.add(CompressionResult(it.input, s0, s0, File(it.metadata.sourcePath)))
                    it.output.delete()
                }
            }
        }
    }

}

