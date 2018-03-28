package com.android.mvp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/3/26.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance = null;
    private static Context mContext;

    public static MyApplication getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
    }
}
