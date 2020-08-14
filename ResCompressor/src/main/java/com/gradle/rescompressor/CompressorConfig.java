package com.gradle.rescompressor;

/**
 * Created by grassswwang
 * on 2020/8/14
 * Email: grassswwang@tencent.com
 */
public class CompressorConfig {

    public int webp_quality = 80;
    public String webp_path = "";

    public void webp_quality(int webp_quality) {
        this.webp_quality = webp_quality;
    }

    public void webp_path(String webp_path) {
        this.webp_path = webp_path;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("<<<<<<<<<<<<<<ResCompresssorConfig>>>>>>>>>>>>\n");
        result.append("webp_quality=" + webp_quality + " \n");
        result.append("webp_path=" + webp_path + "\n");
        result.append("<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>");
        return result.toString();
    }
}
