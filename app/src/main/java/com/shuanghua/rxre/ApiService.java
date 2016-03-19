package com.shuanghua.rxre;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ShuangHua
 */
public interface ApiService {

    //http://m2.qiushibaike.com/article/list/suggest?page=1
    @GET("suggest")
    Observable<BaiKe> getBaKeData(@Query("page") int page);
}
