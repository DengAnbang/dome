package com.home.dab.dome.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.home.dab.dome.R;

/**
 * Created by DAB on 2016/12/12 16:45.
 */

public class RoundProgressBar extends View{
    private Paint mPaint;
    private int mRoundColor;//圆圈的颜色
    private int roundProgressColor;//进度的颜色
    private float roundWidth;//圆圈的宽度
    private int max;
    private int progress;

    private int textColor;


    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mRoundColor = getResources().getColor(R.color.colorAccent);
        roundProgressColor = getResources().getColor(R.color.colorPrimaryDark);
        max = 100;
        progress = 50;
        roundWidth = 50;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 画最外层的大圆环
         */
        int centre = getWidth()/2; //获取圆心的x坐标
        int radius = (int) (centre - roundWidth/2); //圆环的半径
        mPaint.setColor(mRoundColor); //设置圆环的颜色
        mPaint.setStyle(Paint.Style.STROKE); //设置空心
        mPaint.setStrokeWidth(roundWidth); //设置圆环的宽度
        mPaint.setAntiAlias(true);  //消除锯齿
        canvas.drawCircle(centre, centre, radius, mPaint); //画出圆环



        /**
         * 画圆弧 ，画圆环的进度
         */

        //设置进度是实心还是空心
        mPaint.setStrokeWidth(roundWidth); //设置圆环的宽度
        mPaint.setColor(roundProgressColor);  //设置进度的颜色
        RectF oval = new RectF(
                centre - radius,
                centre - radius,
                centre + radius,
                centre + radius);  //用于定义的圆弧的形状和大小的界限
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, 0, 360 * progress / max, false, mPaint);  //根据进度画圆弧

    }
    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if(progress < 0){
            throw new IllegalArgumentException("progress not less than 0");
        }
        if(progress > max){
            progress = max;
        }
        if(progress <= max){
            this.progress = progress;
            postInvalidate();
        }
    }

    public int getRoundColor() {
        return mRoundColor;
    }

    public void setRoundColor(int roundColor) {
        mRoundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }
}
