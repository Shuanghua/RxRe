package com.shuanghua.rxre;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiService管理器，方便有新的基地址加入
 * Created by ShuangHua
 */
public class ServiceManager {

    //只要 BaseUrl 不变，全程只需要一个 Service 对象就够了

    public static final String BASE_URL_BAIKE = "http://m2.qiushibaike.com/article/list/";
    private static final int DEFAULT_TIMEOUT = 5;
    private final ApiService mService;

    /**
     * Service 对象生成机器
     */
    public ServiceManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_BAIKE)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mService = retrofit.create(ApiService.class);
    }

    /**
     * 生成后让工厂送出
     */
    public ApiService getApiService() {
        return mService;
    }
}
