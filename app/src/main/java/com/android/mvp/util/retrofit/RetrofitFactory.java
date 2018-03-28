package com.android.mvp.util.retrofit;


import com.android.mvp.SessionCenter;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2018/3/12.
 * Retrofit2+ OkHttp3+RxJava2
 */

public class RetrofitFactory {
    private static RetrofitFactory mRetrofitFactory;
    private static Retrofit retrofit;

    public static RetrofitFactory getInstance() {
        RetrofitFactory inst = mRetrofitFactory;
        if (inst == null) {
            synchronized (RetrofitFactory.class) {
                inst = mRetrofitFactory;
                if (inst == null) {
                    inst = new RetrofitFactory();
                    mRetrofitFactory = inst;
                }
            }
        }
        return inst;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (retrofit == null) {
                    OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(SessionCenter.HTTP_TIME, TimeUnit.SECONDS)
                            .readTimeout(SessionCenter.HTTP_TIME, TimeUnit.SECONDS)
                            .writeTimeout(SessionCenter.HTTP_TIME, TimeUnit.SECONDS)
                            .addInterceptor(InterceptorUtil.HeaderInterceptor())
                            .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                            .build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(SessionCenter.URL_ROOT)
                            .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                            .client(mOkHttpClient)
                            .build();
                }
            }
        }
        return retrofit;
    }


}
