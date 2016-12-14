package com.home.dab.dome.demo.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.home.dab.dome.R;
import com.home.dab.dome.demo.view.ChartView;

import java.util.ArrayList;
import java.util.List;

public class BrokenLineView extends AppCompatActivity {

    private ChartView mChartView;
    private List<Point> mPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broken_line_view);
        mChartView = (ChartView) findViewById(R.id.cv);
        mPoints = new ArrayList<>();
        mPoints.add(new Point(0,0));
        mPoints.add(new Point(100,100));
        mPoints.add(new Point(200,200));
        mPoints.add(new Point(600,600));

    }

    public void click(View view) {
        mChartView.setData(mPoints,0);
    }
}
