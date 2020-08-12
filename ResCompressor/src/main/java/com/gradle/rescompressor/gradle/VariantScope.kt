package com.gradle.rescompressor.gradle

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.scope.VariantScope
import com.android.sdklib.BuildToolInfo
import java.io.File

private val EXTENSION_GETTER = VariantScopeV40::getExtension


private val ALL_ARTIFACTS_GETTER = VariantScopeV40::getAllArtifacts

private val AAR_GETTER = VariantScopeV40::getAar

private val ALL_CLASSES_GETTER = VariantScopeV40::getAllClasses

private val APK_GETTER = VariantScopeV40::getApk

private val JAVAC_GETTER = VariantScopeV40::getJavac

private val MERGED_ASSETS_GETTER = VariantScopeV40::getMergedAssets

private val MERGED_MANIFESTS_GETTER = VariantScopeV40::getMergedManifests

private val MERGED_RESOURCE_GETTER = VariantScopeV40::getMergedRes

private val PROCESSED_RES_GETTER = VariantScopeV40::getProcessedRes

private val SYMBOL_LIST_GETTER = VariantScopeV40::getSymbolList

private val SYMBOL_LIST_WITH_PACKAGE_NAME_GETTER = VariantScopeV40::getSymbolListWithPackageName

private val BUILD_TOOLS_GETTER = VariantScopeV40::getBuildTools

private val RAW_ANDROID_RESOURCES_GETTER = VariantScopeV40::getRawAndroidResources


val VariantScope.extension: BaseExtension
    get() = EXTENSION_GETTER(this)

val VariantScope.aar: Collection<File>
    get() = AAR_GETTER(this)

/**
 * The output directory of APK files
 */
val VariantScope.apk: Collection<File>
    get() = APK_GETTER(this)

val VariantScope.javac: Collection<File>
    get() = JAVAC_GETTER(this)

/**
 * The output directory of merged [AndroidManifest.xml](https://developer.android.com/guide/topics/manifest/manifest-intro)
 */
val VariantScope.mergedManifests: Collection<File>
    get() = MERGED_MANIFESTS_GETTER(this)

/**
 * The output directory of merged resources
 */
val VariantScope.mergedRes: Collection<File>
    get() = MERGED_RESOURCE_GETTER(this)

/**
 * The output directory of merged assets
 */
val VariantScope.mergedAssets: Collection<File>
    get() = MERGED_ASSETS_GETTER(this)

/**
 * The output directory of processed resources: *resources-**variant**.ap\_*
 */
val VariantScope.processedRes: Collection<File>
    get() = PROCESSED_RES_GETTER(this)

/**
 * All of classes
 */
val VariantScope.allClasses: Collection<File>
    get() = ALL_CLASSES_GETTER(this)

val VariantScope.symbolList: Collection<File>
    get() = SYMBOL_LIST_GETTER(this)

val VariantScope.symbolListWithPackageName: Collection<File>
    get() = SYMBOL_LIST_WITH_PACKAGE_NAME_GETTER(this)

val VariantScope.allArtifacts: Map<String, Collection<File>>
    get() = ALL_ARTIFACTS_GETTER(this)

val VariantScope.buildTools: BuildToolInfo
    get() = BUILD_TOOLS_GETTER(this)

val VariantScope.rawAndroidResources: Collection<File>
    get() = RAW_ANDROID_RESOURCES_GETTER(this)


