package com.grass.helloandroid.webview

import android.os.Bundle
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
        val url = "https://www.baidu.com"
        webview.loadUrl(url)
    }

    fun setWebSettings(settings: WebSettings) {
        settings.javaScriptEnabled = true
    }

}
