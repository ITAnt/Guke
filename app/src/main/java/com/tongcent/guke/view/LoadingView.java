package com.tongcent.guke.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class LoadingView extends View {

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Paint paint = new Paint();
        // 笔触颜色
        paint.setColor(Color.parseColor("#b2a07c"));
        // 笔触粗细
        paint.setStrokeWidth(5);
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(width/2, height/3, width/2, height*23/60, paint);
            canvas.rotate(30, width/2, height/2);
        }

    }
}
