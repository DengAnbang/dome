package com.home.dab.dome.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.home.dab.dome.R;
import com.home.dab.dome.demo.activity.Download;
import com.home.dab.dome.demo.activity.SilentInstallation;
import com.home.dab.dome.demo.view.RoundProgressBar;
import com.home.dab.dome.shoppingcart.ShoppingMainActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RoundProgressBar mRoundProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoundProgressBar = (RoundProgressBar) findViewById(R.id.rp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRoundProgressBar.setRoundProgressColor(ContextCompat.getColor(this,R.color.colorPrimary));

    }

    public void download(View view) {
//        mRoundProgressBar.setProgress(40);
        startActivity(new Intent(MainActivity.this, Download.class));

    }

    public void install(View view) {
        startActivity(new Intent(MainActivity.this, SilentInstallation.class));
    }

    public void shopping(View view) {
        startActivity(new Intent(MainActivity.this, ShoppingMainActivity.class));
    }
}
