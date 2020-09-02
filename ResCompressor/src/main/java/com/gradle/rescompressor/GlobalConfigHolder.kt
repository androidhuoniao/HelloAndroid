package com.gradle.rescompressor

/**
 *
 * Created by grassswwang
 * on 2020/8/14
 * Email: grassswwang@tencent.com
 */
object GlobalConfigHolder {
    var mConfig: CompressorConfig = CompressorConfig();
    fun setConfig(config: CompressorConfig) {
        mConfig = config;
    }

    fun getConfig(): CompressorConfig {
        return mConfig;
    }

}