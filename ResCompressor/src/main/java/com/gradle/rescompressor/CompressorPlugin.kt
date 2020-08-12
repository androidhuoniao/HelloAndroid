package com.gradle.rescompressor

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * Created by grassswwang
 * on 2020/8/12
 * Email: grassswwang@tencent.com
 */
class CompressorPlugin :Plugin<Project>{

    override fun apply(proj: Project) {
        println("${this.javaClass.simpleName}.apply() is working")
    }
}