package com.grass.helloandroid.span;

import android.graphics.drawable.Drawable;

/**
 * Created by grassswwang
 * on 2019-09-20
 * Email: grassswwang@tencent.com
 */
public class TopIndexSpanParams {
    public Drawable iconDrawable;
    public Drawable bgDrawable;

    public int indexFontSize;
    public int indexColor;
    public int indexMarginLeft;

    public int contentPaddingLeft;
    public int contentPaddingRight;

    public int spanMarginRight;

    private TopIndexSpanParams(Builder builder) {
        iconDrawable = builder.iconDrawable;
        bgDrawable = builder.bgDrawable;
        indexFontSize = builder.indexFontSize;
        indexColor = builder.indexColor;
        indexMarginLeft = builder.indexMarginLeft;
        contentPaddingLeft = builder.contentPaddingLeft;
        contentPaddingRight = builder.contentPaddingRight;
        spanMarginRight = builder.spanMarginRight;
    }



    public static final class Builder {
        private Drawable iconDrawable;
        private Drawable bgDrawable;
        private int indexFontSize;
        private int indexColor;
        private int indexMarginLeft;
        private int contentPaddingLeft;
        private int contentPaddingRight;
        private int spanMarginRight;

        public Builder() {
        }

        public Builder iconDrawable(Drawable val) {
            iconDrawable = val;
            return this;
        }

        public Builder bgDrawable(Drawable val) {
            bgDrawable = val;
            return this;
        }

        public Builder indexFontSize(int val) {
            indexFontSize = val;
            return this;
        }

        public Builder indexColor(int val) {
            indexColor = val;
            return this;
        }

        public Builder indexMarginLeft(int val) {
            indexMarginLeft = val;
            return this;
        }

        public Builder contentPaddingLeft(int val) {
            contentPaddingLeft = val;
            return this;
        }

        public Builder contentPaddingRight(int val) {
            contentPaddingRight = val;
            return this;
        }

        public Builder spanMarginRight(int val) {
            spanMarginRight = val;
            return this;
        }

        public TopIndexSpanParams build() {
            return new TopIndexSpanParams(this);
        }
    }
}
