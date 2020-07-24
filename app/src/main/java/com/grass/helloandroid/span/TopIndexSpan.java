package com.grass.helloandroid.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TopIndexSpan extends ImageSpan {

    private static final String TAG = "TopIndexSpan";

    private String mIndex;
    private int mIndexStrSize;

    private int mBgWidth;
    private Paint mIndexPaint;

    private Drawable mBackGroundDrawable;

    private TopIndexSpanParams mSpanParams;

    public TopIndexSpan(int index, TopIndexSpanParams params) {
        super(params.iconDrawable);
        mSpanParams = params;
        this.mBackGroundDrawable = params.bgDrawable;
        mIndex = String.valueOf(index);
        initIndexPaint();
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        int originalSize = super.getSize(paint, text, start, end, fm);
        mIndexStrSize = (int) (mIndexPaint.measureText(String.valueOf(mIndex), 0, mIndex.length()));
        int spanSize = originalSize + mIndexStrSize + mSpanParams.contentPaddingLeft + mSpanParams.contentPaddingRight + mSpanParams.indexMarginLeft;
        mBgWidth = spanSize;
        Log.d(TAG, "getSize: originalSize: " + originalSize + " spanSize: " + spanSize + " mIndexStrSize: " + mIndexStrSize);
        return spanSize + mSpanParams.spanMarginRight;
    }

    private void initIndexPaint() {
        mIndexPaint = new Paint();
        mIndexPaint.setTextSize(mSpanParams.indexFontSize);
        mIndexPaint.setColor(mSpanParams.indexColor);
        mIndexPaint.setAntiAlias(true);// 设置画笔的锯齿效果
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end,
                     float x, int top, int y, int bottom, Paint paint) {
        Log.d(TAG, "draw() called with: " + "start = [" + start + "], end = [" + end + "], x = [" + x + "], top = [" + top + "], y = ["
                + y + "], bottom = [" + bottom + "], paint = [ ascent: "
                + (paint.ascent() + " descent: " + paint.descent() + " all: " + (paint.descent() - paint.ascent())) + "] "
                + " textsize: " + paint.getTextSize());
        final long starttime = System.currentTimeMillis();

        Drawable drawable = getDrawable();
        if (drawable != null) {
            // 绘制圆角背景
            canvas.save();
            Rect rect = drawable.getBounds();
            int textHeight = (int) (paint.descent() - paint.ascent());

//            Log.d(Constants.TAG, "draw: lineHeight: " + lineHeight + " textHeight: " + textHeight);
            mBackGroundDrawable.setBounds(0, 0, mBgWidth, textHeight);
            mBackGroundDrawable.draw(canvas);
            canvas.restore();

            int middleBaseLineY = textHeight / 2;

            // 绘制icon
            canvas.save();
            int transY = middleBaseLineY - rect.bottom / 2;
            canvas.translate(x + mSpanParams.contentPaddingLeft, transY);
            drawable.draw(canvas);
            canvas.restore();

            // 绘制文字
            canvas.save();
            float indexStringHeight = (mIndexPaint.descent() - mIndexPaint.ascent());
            float indexY = y * (indexStringHeight / (textHeight * 1f));
            float indexX = x + rect.right + mSpanParams.contentPaddingLeft + mSpanParams.indexMarginLeft;

            canvas.drawText(mIndex, 0, mIndex.length(), indexX, indexY, mIndexPaint);//绘制文字
            Log.i(TAG, "draw: indexY: " + indexY);
            canvas.restore();
        }
        Log.i("profiling", "[draw]:" + (System.currentTimeMillis() - starttime));
    }

}

