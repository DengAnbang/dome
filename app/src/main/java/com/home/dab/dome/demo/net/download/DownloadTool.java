package com.home.dab.dome.demo.net.download;

import com.home.dab.dome.demo.net.ApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.home.dab.dome.demo.Constant.baseUrl;

/**
 * Created by DAB on 2016/12/12 11:03.
 */

public class DownloadTool {
    //保存文件
    public static void saveFile(long startIndex, InputStream inputStream, String fileStoreDir, String fileName) throws IOException {
        byte[] buf = new byte[2048];
        int len;
        try {
            File dir = new File(fileStoreDir);
            if (!dir.exists()) {
                boolean mkdirs = dir.mkdirs();

            }
            File file = new File(dir, fileName);
//            if (!file.exists()) {
//                boolean mkdirs = file.mkdirs();
//
//            }
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(startIndex);
            while ((len = inputStream.read(buf)) != -1) {
                raf.write(buf, 0, len);
            }
            raf.close();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取一个下载时候的OkHttpClient
     * @param downloadCallback
     * @return
     */
    public static OkHttpClient getOkHttpClient(IDownloadCallback downloadCallback) {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> chain.proceed(chain.request()).newBuilder()
                        .body(new DownloadResponseBody(chain.proceed(chain.request()),downloadCallback))
                        .build()).build();
    }

    /**
     * 获取一个下载的ApiService
     * @param downloadCallback
     * @return
     */
    public static ApiService getApiService(IDownloadCallback downloadCallback) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(DownloadTool.getOkHttpClient(downloadCallback))
                .build()
                .create(ApiService.class);
    }
}
