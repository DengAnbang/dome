package com.home.dab.dome.shoppingcart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.home.dab.dome.R;
import com.home.dab.dome.shoppingcart.adapter.ViewPagerApt;
import com.home.dab.dome.shoppingcart.fragment.StoreFragment;
import com.home.dab.dome.shoppingcart.fragment.TestFragment;
import com.home.dab.dome.shoppingcart.fragment.TestFragment2;

import java.util.ArrayList;
import java.util.List;

public class ShoppingMainActivity extends AppCompatActivity {
    private ViewPager mVpContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_main);
        mVpContent = (ViewPager) findViewById(R.id.vp_content);
        initData();
    }

    private void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new StoreFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment2());
        ViewPagerApt viewPagerApt = new ViewPagerApt(getSupportFragmentManager(), fragments);
        mVpContent.setAdapter(viewPagerApt);
    }
}
