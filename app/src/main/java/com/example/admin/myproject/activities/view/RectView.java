package com.example.admin.myproject.activities.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2/6/2018.
 */

public class RectView extends View {

    private Paint paint;
    private int rectWidth = 100;  //default rectangle width
    private int rectHeight = 100;  //default rectangle height
    private float angle;

    public RectView(Context context) {
        super(context);
        init();
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.drawRect(getWidth() / 2 - rectWidth, getHeight() / 2 - rectHeight,
                getWidth() / 2 + rectWidth, getHeight() / 2 + rectHeight, paint);
    }

    public void rotate(float angle) {
        this.angle = angle;
        invalidate();
    }

    public void reset() {
        this.angle=0;
        invalidate();
    }
}
