package com.grass.helloandroid.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.grass.helloandroid.R
import com.grass.helloandroid.inflate
import kotlinx.android.synthetic.main.list_item_preview.view.*

/**
 *
 * Created by grassswwang
 * on 2020/7/10
 * Email: grassswwang@tencent.com
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class PreviewItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = VERTICAL
        inflate(R.layout.list_item_preview)
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        titleView.text = title
    }

    @ModelProp
    fun setIcon(@DrawableRes icon: Int) {
        iconView.setImageResource(icon)
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        container.setOnClickListener(clickListener)
    }
}