package com.gradle.rescompressor.task.compression.cwebp

import com.android.build.gradle.api.BaseVariant
import com.gradle.rescompressor.Build
import com.gradle.rescompressor.CompressorConfig
import com.gradle.rescompressor.compression.CompressionResults
import com.gradle.rescompressor.compression.generateReport
import com.gradle.rescompressor.compression.isFlatPngExceptRaw
import com.gradle.rescompressor.compression.isPngExceptRaw
import com.gradle.rescompressor.gradle.aapt2Enabled
import com.gradle.rescompressor.gradle.mergeResourcesTask
import com.gradle.rescompressor.gradle.mergedRes
import com.gradle.rescompressor.gradle.project
import com.gradle.rescompressor.gradle.scope
import com.gradle.rescompressor.kotlinx.search
import com.gradle.rescompressor.utils.LogUtil

/**
 * @author johnsonlee
 */
class CwebpCompressionVariantProcessor {

    fun process(variant: BaseVariant,config: CompressorConfig) {
        LogUtil.log("${variant.project.name} ${variant.name} process is working")
        val results = CompressionResults()
        LogUtil.log("aapt2Enabled ${variant.project.aapt2Enabled}")
        val filter = if (variant.project.aapt2Enabled) ::isFlatPngExceptRaw else ::isPngExceptRaw
        Cwebp.get(variant)?.newCompressionTaskCreator()
            ?.createCompressionTask(variant, results, "resources", {
                var search = variant.scope.mergedRes.search(filter)
                LogUtil.log("variant.scope.mergedRes: "+search.size)
                search
            }, variant.mergeResourcesTask)?.doLast {
                LogUtil.log("CompressionTask.doLast is working")
            results.generateReport(variant, Build.ARTIFACT)
        }
    }

}