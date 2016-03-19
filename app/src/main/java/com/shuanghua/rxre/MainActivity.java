package com.shuanghua.rxre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.text)
    TextView mText;
    @Bind(R.id.button)
    Button mButton;
    public static final ApiService mService = ServiceFactory.getService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mButton.setOnClickListener(v -> getData());
    }

    public void getData() {
        mService.getBaKeData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baiKe -> mText.setText(baiKe
                        .getItems()
                        .get(7)
                        .getContent()));
    }

    private void getBaiKeDatas() {
        String baiKeUrl = "http://m2.qiushibaike.com/article/list/";
        //第一 retrofit 对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baiKeUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//增加对 rxjava 的支持
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //第二 Service 对象
        ApiService baiKeService = retrofit.create(ApiService.class);
        //第三 调用Service里获取数据的方法
        Observable<BaiKe> baKeData = baiKeService.getBaKeData(1);
        //第四 请求并处理数据
        baiKeService.getBaKeData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaiKe>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaiKe baiKe) {
                        mText.setText(baiKe.getItems().get(7).getContent());
                    }
                });
    }
}
