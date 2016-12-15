package com.home.dab.dome.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.home.dab.dome.R;
import com.home.dab.dome.demo.view.chartView.ChartRecyclerApt;
import com.home.dab.dome.demo.view.chartView.ChartView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BrokenLineView extends AppCompatActivity {

    private ChartView mChartView;
    private List<Float> mPoints;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broken_line_view);
        mPoints = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            mPoints.add((float) random.nextInt(300));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ChartRecyclerApt chartRecyclerApt = new ChartRecyclerApt(this, mPoints);
        mRecyclerView.setAdapter(chartRecyclerApt);
//        mChartView = (ChartView) findViewById(R.id.cv);


    }

    public void click(View view) {
//        mChartView.setData(mPoints,0);
    }
}
