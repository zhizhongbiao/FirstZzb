package com.ybs.reslib.widgets.progressbar;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ybs.reslib.R;


public class RoundedRectProgressBar extends View {

    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int barColor;
    private int backColor;
    private int textColor;
    private float radius;

    int progress = 0;

    public RoundedRectProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        /*获取自定义参数的颜色值*/
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedRectProgressBar, defStyle, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.RoundedRectProgressBar_backColor) {
                backColor = a.getColor(attr, Color.GRAY);

            } else if (attr == R.styleable.RoundedRectProgressBar_barColor) {
                barColor = a.getColor(attr, Color.GREEN);

            } else if (attr == R.styleable.RoundedRectProgressBar_textColor) {
                textColor = a.getColor(attr, Color.GREEN);

            }

        }
        a.recycle();
    }

    public RoundedRectProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedRectProgressBar(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // radius = this.getMeasuredHeight() / 5;
        radius = dip2Px(20);
    }
    public  int dip2Px(int dip) {
        // px/dp = density
        // px/(ppi/160) = px
        float density = getContext().getResources().getDisplayMetrics().density;//1.5
        int ppi = getContext().getResources().getDisplayMetrics().densityDpi;//240
        int px = (int) (dip * density + .5f);
        return px;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //背景
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight()), radius, radius, mPaint);
        //进度条
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0, 0, this.getMeasuredWidth() * progress / 100f, this.getMeasuredHeight()), radius, radius, mPaint);

    }

    /*设置进度条进度, 外部调用*/
    public void setProgress(int progress) {
        if (progress > 100) {
            this.progress = 100;
        } else if (progress < 0) {
            this.progress = 0;
        } else {
            this.progress = progress;
        }
        postInvalidate();
    }
}