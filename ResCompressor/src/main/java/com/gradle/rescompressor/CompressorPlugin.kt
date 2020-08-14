package com.gradle.rescompressor

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.gradle.rescompressor.gradle.getAndroid
import com.gradle.rescompressor.task.compression.cwebp.CwebpCompressionVariantProcessor
import com.gradle.rescompressor.test.TestUnits
import com.gradle.rescompressor.utils.LogUtil
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * Created by grassswwang
 * on 2020/8/12
 * Email: grassswwang@tencent.com
 */
class CompressorPlugin : Plugin<Project> {

    private lateinit var mConfig: CompressorConfig

    override fun apply(project: Project) {
        println("${project.name} ${this.javaClass.simpleName}.apply() is working")
        createConfigExtension(project)
        when {
            project.plugins.hasPlugin("com.android.application") -> project.getAndroid<AppExtension>()
                .let { android ->
                    project.afterEvaluate {
                        LogUtil.log("${project.name} afterEvaluate is working")
                        getConfig(project)
                        TestUnits.test()
                        var processor = CwebpCompressionVariantProcessor()
                        android.applicationVariants.forEach { variant ->
                            processor.process(variant,mConfig)
                        }
                    }
                }
            project.plugins.hasPlugin("com.android.library") -> project.getAndroid<LibraryExtension>()
                .let { android ->
                    project.afterEvaluate {
                        LogUtil.log("${project.name} afterEvaluate is working")
                        var processor = CwebpCompressionVariantProcessor()
                        android.libraryVariants.forEach { variant ->
                            processor.process(variant,mConfig)
                        }
                    }
                }
        }
    }

    fun createConfigExtension(project: Project) {
        project.extensions.create(Constants.CONFIG_NAME, CompressorConfig::class.java)
    }

    fun getConfig(project: Project) {
        mConfig = project.property(Constants.CONFIG_NAME) as CompressorConfig
        LogUtil.log("$mConfig")
    }

}