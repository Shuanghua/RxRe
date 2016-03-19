package com.shuanghua.rxre;

/**
 * 负责获取 mService 对象
 * 具体的生成交给机器（管理器）
 * Created by ShuangHua
 */
public class ServiceFactory {
    public static final Object monitor = new Object();
    public static final int page = 1;
    static ApiService mService = null;

    public static ApiService getService() {
        synchronized (monitor) {
            if (mService == null) {
                //叫机器生产，坐等产品
                mService = new ServiceManager().getApiService();
            }
            return mService;//出厂销售。。。
        }
    }
}
