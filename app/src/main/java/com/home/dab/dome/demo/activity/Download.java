package com.home.dab.dome.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.home.dab.dome.demo.Constant;
import com.home.dab.dome.R;
import com.home.dab.dome.demo.net.NetClass;
import com.home.dab.dome.demo.net.download.IDownloadCallback;

public class Download extends AppCompatActivity {
    private static final String TAG = "Download";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
    }

    public void download(View view) {
        NetClass.getInstance().downloadFile(Constant.baseUrl + Constant.fileName, Constant.fileStoreDir, Constant.fileName, new IDownloadCallback() {
            @Override
            public void onProgressChange(long progress, long total) {
                Log.e(TAG, "onProgressChange: " + progress + "****" + total);

            }

            @Override
            public void onPauseDownload(long haveDownloaded, long total) {
                Log.e(TAG, "onPauseDownload: " + haveDownloaded);
//                NetClass.getInstance().mInfo = new DownloadInfo(Constant.fileStoreDir, Constant.baseUrl + Constant.fileName, total);
//                DownloadInfo downloadInfo = DBManager.getInstance().queryDownloadInfoList(Constant.baseUrl + Constant.fileName);
//                downloadInfo.setBytesReaded(haveDownloaded);
//                downloadInfo.setContentLength(total);
//                DBManager.getInstance().insertDownloadInfo(downloadInfo);
            }
        });
    }

    public void pause(View view) {
        NetClass.getInstance().mDisposable.dispose();
    }
}
