package com.home.dab.dome.demo.view.chartView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.home.dab.dome.R;

import java.util.List;

/**
 * Created by DAB on 2016/12/14 13:27.
 * 折线统计的子布局
 */

public class ChartView extends View {
    private static final String TAG = "ChartView";
    private int mBrokenLineColor;
    private int mGridLineColor;
    private int mWidth, mHeight;
    private int mGridCountX, mGridCountY;
    private float mChildGridWidth;
    private float mChildGridHeight;
    private int mBoldLineWidth, mFineLineWidth;
    private Paint mGridPaint, mBrokenLinePaint;
    private Path mPath;
    private float mScaleY;
    private int mMaxY;
    //当大小确定的时候 通知
    private IOnSizeChangedFinish mOnSizeChangedFinish;

    public void setOnSizeChangedFinish(IOnSizeChangedFinish onSizeChangedFinish) {
        mOnSizeChangedFinish = onSizeChangedFinish;
    }

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setBrokenLineColor(int brokenLineColor) {
        mBrokenLineColor = brokenLineColor;
    }

    public void setMaxY(int maxY) {
        mMaxY = maxY;
    }

    public void setGridCountX(int gridCountX) {
        mGridCountX = gridCountX;
    }

    private void init() {
        mGridCountY = 30;
        mMaxY = 300;
        mScaleY = mMaxY / mGridCountY;
        mGridCountX = 5;
        mBoldLineWidth = 5;
        mFineLineWidth = 1;
        mGridLineColor = ContextCompat.getColor(getContext(), R.color.colorBlack);
        mBrokenLineColor = ContextCompat.getColor(getContext(), R.color.colorAccent);
        mGridPaint = new Paint();
        mGridPaint.setStyle(Paint.Style.STROKE);
        mGridPaint.setAntiAlias(true);
        mBrokenLinePaint = new Paint();
        mBrokenLinePaint.setStyle(Paint.Style.STROKE);
        mBrokenLinePaint.setAntiAlias(true);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        mChildGridWidth = (float) (mWidth - mGridCountX - 1) / mGridCountX;
        int y = (mGridCountY / 5) * (mBoldLineWidth - 1) + mGridCountY + 1;
        mChildGridHeight = (float) (mHeight - y) / mGridCountY;
        if (mOnSizeChangedFinish != null) {
            mOnSizeChangedFinish.finish();
        } else {
            throw new RuntimeException("没有传入IOnSizeChangedFinish回调");
        }
//        mChildGridWidth = (float) (mWidth - mGridCountX - 1) / mGridCountX;
//        int y = (mGridCountY / 5) * (mBoldLineWidth - 1) + mGridCountY + 1;
//        mChildGridHeight = (float) (mHeight - y) / mGridCountY;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
//        Log.e(TAG, "onMeasure: " + mHeight + "*****" + mWidth);
//        mChildGridWidth = (float) (mWidth - mGridCountX - 1) / mGridCountX;
//        int y = (mGridCountY / 5) * (mBoldLineWidth - 1) + mGridCountY + 1;
//        mChildGridHeight = (float) (mHeight - y) / mGridCountY;
//        Log.e(TAG, "onMeasure: " + mChildGridHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mGridPaint.setColor(mGridLineColor);
        for (int i = 0; i <= mGridCountY; i++) {
            mGridPaint.setStrokeWidth((i % 5 != 0) ? mFineLineWidth : mBoldLineWidth);
            canvas.drawLine(0, getChildGridY(i), getChildGridX(mGridCountX), getChildGridY(i), mGridPaint);
        }
        for (int i = 0; i <= mGridCountX; i++) {
            mGridPaint.setStrokeWidth((i == 0 || i == mGridCountX) ? mBoldLineWidth : mFineLineWidth);
            canvas.drawLine(getChildGridX(i), 0, getChildGridX(i), getChildGridY(mGridCountY), mGridPaint);
        }
        if (mPath != null) {

//            canvas.translate(getChildGridX(0),getChildGridY(mGridCountY));
            mBrokenLinePaint.setColor(mBrokenLineColor);
            mBrokenLinePaint.setStrokeWidth(mBoldLineWidth);
            canvas.drawPath(mPath, mBrokenLinePaint);
        }

    }

    private float getChildGridX(int position) {
        return (float) (position * mChildGridWidth + mBoldLineWidth / 2.0);
    }

    private float getChildGridY(int position) {
        return (float) (position * mChildGridHeight + mBoldLineWidth / 2.0);
    }

    private float getChildGridYByScaleY(float remainder) {
        return (float) ((remainder / mScaleY) * mChildGridHeight + mBoldLineWidth / 2.0);
    }

    private float getChildGridYByFloat(float v) {
        return getChildGridY(mGridCountY)
                - getChildGridY((int) (v / mScaleY))
                - getChildGridYByScaleY(v % mScaleY)
                + (mBoldLineWidth / 2.0f);
    }


    public void setData(List<Float> floats, int position) {
        if (floats == null) throw new RuntimeException("传入的Data为空");
        mPath = new Path();
        mPath.moveTo(getChildGridX(0), getChildGridYByFloat(floats.get(position * mGridCountX)));
        int childGridX = 0;
        for (int i = position * mGridCountX; i <= position * mGridCountX + 5; i++) {
            if (floats.size() <= i) continue;
            mPath.lineTo(getChildGridX(childGridX), getChildGridYByFloat(floats.get(i)));
            childGridX++;
//            Log.e(TAG, "setData: " + position + "*****" + floats.get(i) + "****" + getChildGridX(i) + "*****" + getChildGridYByFloat(floats.get(i)));
//            Log.e(TAG, "setData: " + floats.get(i) + "****" + getChildGridYByFloat(floats.get(i)));
        }
        invalidate();
    }
}
