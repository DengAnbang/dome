package com.home.dab.dome.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.home.dab.dome.R;
import com.home.dab.dome.shoppingcart.Data;

import java.util.List;

/**
 * Created by DAB on 2016/12/13 14:01.
 */

public class NavigationApt extends RecyclerView.Adapter<NavigationApt.ViewHolder> {
    private List<Data> mDatas;
    private Context mContext;

    public NavigationApt(List<Data> datas, Context context) {
        mDatas = datas;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_navigation_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data = mDatas.get(position);
        if (data.getSelectSum() < 1) {
            holder.mTvNum.setVisibility(View.GONE);
        } else {
            holder.mTvNum.setVisibility(View.VISIBLE);
            holder.mTvNum.setText(data.getSelectSum() + "");
        }
        holder.mTvTitle.setText(data.getTitle());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle, mTvNum;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.item_tv);
            mTvNum = (TextView) itemView.findViewById(R.id.item_num);
        }
    }
}
