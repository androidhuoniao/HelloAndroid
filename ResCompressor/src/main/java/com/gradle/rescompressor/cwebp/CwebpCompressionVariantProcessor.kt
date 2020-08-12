package com.gradle.rescompressor.task.compression.cwebp

import com.android.build.gradle.api.BaseVariant
import com.gradle.rescompressor.Build
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

/**
 * @author johnsonlee
 */
class CwebpCompressionVariantProcessor  {

     fun process(variant: BaseVariant) {
        val results = CompressionResults()
        val filter = if (variant.project.aapt2Enabled) ::isFlatPngExceptRaw else ::isPngExceptRaw
        Cwebp.get(variant)?.newCompressionTaskCreator()?.createCompressionTask(variant, results, "resources", {
            variant.scope.mergedRes.search(filter)
        }, variant.mergeResourcesTask)?.doLast {
            results.generateReport(variant, Build.ARTIFACT)
        }
    }

}