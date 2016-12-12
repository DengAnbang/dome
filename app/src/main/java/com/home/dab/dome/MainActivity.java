package com.home.dab.dome;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.home.dab.dome.view.RoundProgressBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RoundProgressBar mRoundProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: 大大" );
        mRoundProgressBar = (RoundProgressBar) findViewById(R.id.rp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRoundProgressBar.setRoundProgressColor(ContextCompat.getColor(this,R.color.colorPrimary));

    }

    public void download(View view) {
        mRoundProgressBar.setProgress(40);
//        startActivity(new Intent(MainActivity.this, Download.class));
    }
}
