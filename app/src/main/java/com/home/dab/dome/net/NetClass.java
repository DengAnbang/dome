package com.home.dab.dome.net;

import android.util.Log;

import com.home.dab.dome.net.download.DownloadInfo;
import com.home.dab.dome.net.download.DownloadResponseBody;
import com.home.dab.dome.net.download.DownloadTool;
import com.home.dab.dome.net.download.IDownloadCallback;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.home.dab.dome.Constant.baseUrl;
import static com.home.dab.dome.net.download.DownloadTool.getApiService;


/**
 * Created by DAB on 2016/12/7 09:18.
 *
 */

public class NetClass {
    private static final String TAG = "NetClass";
    private static volatile NetClass instance;
    private final ApiService mApiService;
    public DownloadInfo mInfo;

    private NetClass() {
        mApiService = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
                .create(ApiService.class);
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> chain.proceed(chain.request())
                        .newBuilder()
                        .body(new DownloadResponseBody(chain.proceed(chain.request())))
                        .build())
                .build();

    }


    public static NetClass getInstance() {
        if (instance == null) {
            synchronized (NetClass.class) {
                if (instance == null) {
                    instance = new NetClass();
                }
            }
        }
        return instance;
    }

    public Disposable mDisposable;


    public void downloadFile(String url, String fileStoreDir, String fileName, IDownloadCallback downloadCallback) {
        DownloadInfo downloadInfo = getDownloadInfo(url);
        ApiService apiService = getApiService(downloadCallback);
        if (downloadInfo == null) {
            apiService.download(url)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(Schedulers.io())
                    .map(responseBody -> {
                        DownloadTool.saveFile(0, responseBody.byteStream()
                                , fileStoreDir, fileName);
                        return responseBody;
                    })
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.e(TAG, "onSubscribe: ");
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(ResponseBody value) {
                            Log.e(TAG, "onNext: ");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: ");
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete: ");
                        }
                    });
        } else {
            apiService.download("bytes=" + downloadInfo.getBytesReaded() + "-", downloadInfo.getUrl())
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .map(responseBody -> {
                        DownloadTool.saveFile(downloadInfo.getBytesReaded(), responseBody.byteStream()
                                , fileStoreDir, fileName);
                        Log.e(TAG, "getThreadDownload: 完成" + downloadInfo.getBytesReaded());
                        return responseBody;
                    })
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.e(TAG, "onSubscribe: ");
                            mDisposable = d;
                        }

                        @Override
                        public void onNext(ResponseBody value) {
                            Log.e(TAG, "onNext:sada ");
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            Log.e(TAG, "onComplete: ");
                        }
                    });
        }

    }

    private DownloadInfo getDownloadInfo(String url) {

        return mInfo;
    }

    private DownloadInfo getDownloadInfo(String url, String fileStoreDir, long contentLength) {
        mInfo = new DownloadInfo.Builder()
                .setContentLength(contentLength)
                .setUrl(url)
                .setSavePath(fileStoreDir)
                .Build();
        return mInfo;
    }

}
