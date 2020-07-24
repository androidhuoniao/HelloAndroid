package com.grass.helloandroid.span

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import androidx.appcompat.app.AppCompatActivity
import com.grass.helloandroid.R
import kotlinx.android.synthetic.main.activity_span.*

class SpanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_span)

//            Log.d(Constants.TAG, "draw: lineHeight: " + lineHeight + " textHeight: " + textHeight);
        var drawable = getDrawable(R.mipmap.ic_launcher)
        drawable!!.setBounds(0, 0, 100, 100)
        var spanParams = TopIndexSpanParams.Builder()
            .iconDrawable(drawable)
            .bgDrawable(ColorDrawable(Color.RED))
            .indexFontSize(60)
            .indexColor(Color.WHITE)
            .contentPaddingLeft(20)
            .indexMarginLeft(20)
            .contentPaddingRight(20)
            .spanMarginRight(30)
            .build()
        var title = "topIndex afjdkdajfkajdlkfjalkjfkdjak "
        var spannableTitle = SpannableString(title)
        var topIndexSpan = TopIndexSpan(10, spanParams)
        spannableTitle.setSpan(
            topIndexSpan,
            0,
            "topIndex".length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        tv.text = spannableTitle
    }
}
