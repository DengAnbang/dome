package com.home.dab.dome.shoppingcart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.dab.dome.R;
import com.home.dab.dome.shoppingcart.Data;
import com.home.dab.dome.shoppingcart.adapter.CommodityApt;
import com.home.dab.dome.shoppingcart.adapter.NavigationApt;

import java.util.ArrayList;
import java.util.List;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderDecoration;

/**
 * Created by DAB on 2016/12/13 13:54.
 */

public class StoreFragment extends Fragment {

    private View mView;
    private RecyclerView mRecyclerNavigation, mRecyclerCommodity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_store, container, false);
        initView(mView);
        initData();
        return mView;
    }

    private void initData() {
        List<Data> datas = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            datas.add(new Data(i, "商品分类" + i));
        }

        mRecyclerNavigation.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        NavigationApt navigationApt = new NavigationApt(datas, getActivity());
        mRecyclerNavigation.setAdapter(navigationApt);
        List<Data.Commodity> commodities = new ArrayList<>();

        for (int i = 0; i < datas.size(); i++) {
            for (int j = 0; j < datas.get(i).getCommodities().size(); j++) {
                commodities.add(datas.get(i).getCommodities().get(j));
            }
        }

        mRecyclerCommodity.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        CommodityApt commodityApt = new CommodityApt(getActivity(), commodities);//设置悬浮索引
        StickyHeaderDecoration decor = new StickyHeaderDecoration(commodityApt);
        mRecyclerCommodity.setAdapter(commodityApt);
        mRecyclerCommodity.addItemDecoration(decor,0);

    }

    private void initView(View view) {
        mRecyclerNavigation = (RecyclerView) view.findViewById(R.id.rv_navigation);
        mRecyclerCommodity = (RecyclerView) view.findViewById(R.id.rv_commodity);
    }
}
