package com.example.mtademo;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 廖理 on 2018/1/8.
 */

public class OkhttpManager {

    OkHttpClient.Builder mBuilder = new OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS);

    private static OkhttpManager instance = new OkhttpManager();

    private OkHttpClient client = mBuilder.build();

    public OkhttpManager() {

    }

    public static OkhttpManager getInstance(){
        return instance;
    }

    public void getNetTime(String url, Callback responseCallback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(responseCallback);

    }


}
