package com.gradle.rescompressor

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.gradle.rescompressor.gradle.getAndroid
import com.gradle.rescompressor.task.compression.cwebp.CwebpCompressionVariantProcessor
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * Created by grassswwang
 * on 2020/8/12
 * Email: grassswwang@tencent.com
 */
class CompressorPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("${this.javaClass.simpleName}.apply() is working")
        when {
            project.plugins.hasPlugin("com.android.application") -> project.getAndroid<AppExtension>()
                .let { android ->
                    project.afterEvaluate {
                        var processor = CwebpCompressionVariantProcessor()
                        android.applicationVariants.forEach { variant ->
                            processor.process(variant)
                        }
                    }
                }
            project.plugins.hasPlugin("com.android.library") -> project.getAndroid<LibraryExtension>()
                .let { android ->
                    project.afterEvaluate {
                        var processor = CwebpCompressionVariantProcessor()
                        android.libraryVariants.forEach { variant ->
                            processor.process(variant)
                        }
                    }
                }
        }
    }
}