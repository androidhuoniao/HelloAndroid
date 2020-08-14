package com.gradle.rescompressor.test

import com.android.build.gradle.api.BaseVariant
import com.gradle.rescompressor.gradle.mergedRes
import com.gradle.rescompressor.gradle.scope
import com.gradle.rescompressor.utils.LogUtil

/**
 *
 * Created by grassswwang
 * on 2020/8/14
 * Email: grassswwang@tencent.com
 */
object TestVariantScope {
    fun test(variant: BaseVariant) {
        LogUtil.log("${variant.name} TestVariantScope=============")
        variant.scope.mergedRes.forEach {
            LogUtil.log("${it.path}")
        }
        LogUtil.log("TestVariantScope end =============")
    }
}