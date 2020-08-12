package com.gradle.rescompressor.gradle

import com.android.build.gradle.internal.variant.BaseVariantData

fun BaseVariantData.isAar() =  BaseVariantDataV30.isAar(this)

fun BaseVariantData.isApk() = when {
    GTE_V3_2 -> false
    else -> BaseVariantDataV30.isApk(this)
}

fun BaseVariantData.isBaseModule() = when {
    GTE_V3_2 -> false
    else -> BaseVariantDataV30.isBaseModule(this)
}

fun BaseVariantData.isDynamicFeature() = when {
    GTE_V3_2 -> false
    else -> BaseVariantDataV30.isDynamicFeature(this)
}

fun BaseVariantData.isForTesting() = when {
    GTE_V3_2 -> false
    else -> BaseVariantDataV30.isForTesting(this)
}

fun BaseVariantData.isHybrid() = when {
    GTE_V3_2 -> false
    else -> BaseVariantDataV30.isHybrid(this)
}

fun BaseVariantData.getAnalyticsVariantType() = when {
    GTE_V3_2 -> false
    else -> BaseVariantDataV30.getAnalyticsVariantType(this)
}

fun BaseVariantData.getOriginalApplicationId() = BaseVariantDataV40.getOriginalApplicationId(this)

