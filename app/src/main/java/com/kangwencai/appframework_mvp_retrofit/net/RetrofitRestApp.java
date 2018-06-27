package com.kangwencai.appframework_mvp_retrofit.net;


import com.kangwencai.appframework_mvp_retrofit.MainApplication;
import com.kangwencai.common.net_retrofit.RetrofitRest;

/**
 * Created by kangwencai on 2016/11/9.
 */
public class RetrofitRestApp extends RetrofitRest {

    public static AppNetService appNetService;

    public static void getInstance() {
        synchronized (RetrofitRestApp.class) {
            if (appNetService == null) {
                RetrofitRest.getInstance(MainApplication.getApplication());
                appNetService = RetrofitRest.getRetrofit().create(AppNetService.class);
            }
        }

    }
}
