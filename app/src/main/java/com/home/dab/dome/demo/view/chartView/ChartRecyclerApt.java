package com.home.dab.dome.demo.view.chartView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.dab.dome.R;

import java.util.List;

/**
 * Created by DAB on 2016/12/15 10:54.
 */

public class ChartRecyclerApt extends RecyclerView.Adapter<ChartRecyclerApt.ViewHolder> {
    private List<Float> mFloats;
    private LayoutInflater mLayoutInflater;

    public ChartRecyclerApt(Context context, List<Float> floats) {
        mFloats = floats;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_chart, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mChartView.setBrokenLineColor(R.color.colorBlack);
        holder.mChartView.setOnSizeChangedFinish(() ->
                holder.mChartView.setData(mFloats, position));
    }

    @Override
    public int getItemCount() {
        return (mFloats.size() - 2) / 5 +1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ChartView mChartView;

        public ViewHolder(View itemView) {
            super(itemView);
            mChartView = (ChartView) itemView.findViewById(R.id.item_cv);
        }
    }
}
