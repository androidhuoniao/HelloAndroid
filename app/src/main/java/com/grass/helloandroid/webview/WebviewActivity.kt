package com.grass.helloandroid.webview

import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import com.grass.helloandroid.R
import kotlinx.android.synthetic.main.activity_webview.*

/**
 *
 * Created by grassswwang
 * on 2020/8/3
 * Email: grassswwang@tencent.com
 */
class WebviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        setWebSettings(webview.settings)
//        val url = "https://www.baidu.com"
//        webview.loadUrl(url)
        webview.loadUrl("file:///android_asset/load_android_drawable.html");
    }

    override fun onResume() {
        super.onResume()
        loadimage("largeicon.png");
    }

    fun setWebSettings(settings: WebSettings) {
        settings.javaScriptEnabled = true
    }


    private fun loadimage(imageName: String) {
        val url = "javascript:loadimage('$imageName');"
        Log.e("Javascript Log", url)
        webview.loadUrl(url)
    }
}
