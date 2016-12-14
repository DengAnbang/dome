package com.home.dab.dome.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.home.dab.dome.R;

import java.util.List;

/**
 * Created by DAB on 2016/12/14 13:27.
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
    private Paint mGridPaint;
    private Path mPath;

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mGridCountY = 30;
        mGridCountX = 5;
        mBoldLineWidth = 5;
        mFineLineWidth = 1;
        mGridLineColor = ContextCompat.getColor(getContext(), R.color.colorBlack);
        mBrokenLineColor = ContextCompat.getColor(getContext(), R.color.colorBlack);
        mGridPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        mChildGridWidth = (float) (mWidth - mGridCountX - 1) / mGridCountX;
        int y = (mGridCountY / 5) * (mBoldLineWidth - 1) + mGridCountY + 1;
        mChildGridHeight = (float) (mHeight - y) / mGridCountY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mGridPaint.setColor(mGridLineColor);
        for (int i = 0; i <= mGridCountY; i++) {
            mGridPaint.setStrokeWidth((i % 5 != 0) ? mFineLineWidth : mBoldLineWidth);
            canvas.drawLine(0, (float) (i * mChildGridHeight + mBoldLineWidth / 2.0), (float) mWidth, (float) (i * mChildGridHeight + mBoldLineWidth / 2.0), mGridPaint);
        }
        for (int i = 0; i <= mGridCountX; i++) {
            mGridPaint.setStrokeWidth((i == 0 || i == mGridCountX) ? mBoldLineWidth : mFineLineWidth);
            canvas.drawLine((float) (i * mChildGridWidth + mBoldLineWidth / 2.0), 0, (float) (i * mChildGridWidth + mBoldLineWidth / 2.0), (float) (mGridCountY * mChildGridHeight + mBoldLineWidth / 2.0), mGridPaint);
        }
        if (mPath != null) {
            mGridPaint.setColor(mBrokenLineColor);
            canvas.drawPath(mPath, mGridPaint);
            Log.e(TAG, "onDraw: " );
        }

    }


    public void setData(List<Point> points, int position) {
        if (points == null) throw new RuntimeException("传入的Data为空");

        mPath = new Path();
        for (int i = position*mGridCountX; i < 5; i++) {
            if (points.size()<=i) continue;
            Point point = points.get(i);
            mPath.lineTo(point.x, point.y);
        }
        Log.e(TAG, "setData: " );
        invalidate();
    }
}
