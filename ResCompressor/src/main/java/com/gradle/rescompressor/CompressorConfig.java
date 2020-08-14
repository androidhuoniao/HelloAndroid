package com.gradle.rescompressor;

/**
 * Created by grassswwang
 * on 2020/8/14
 * Email: grassswwang@tencent.com
 */
public class CompressorConfig {

    public int webp_quality = 80;
    public String compressToolsDir = "";

    public void webp_quality(int webp_quality) {
        this.webp_quality = webp_quality;
    }

    public void compressToolsDir(String path) {
        this.compressToolsDir = path;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("<<<<<<<<<<<<<<ResCompresssorConfig>>>>>>>>>>>>\n");
        result.append("webp_quality=" + webp_quality + " \n");
        result.append("compressToolsDir=" + compressToolsDir + "\n");
        result.append("<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>");
        return result.toString();
    }
}
