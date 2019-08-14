package com.vanke.commonlib;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.vanke.commonlib.net.RetrofitUtil;


/**
 * Description: 三方工具初始化，包含分级
 * Author: kangwencai
 * DATA: Date on 2019-08-14.
 * PS: Not easy to write code, please indicate.
 */
public class InitTools {
    static final String TAG = "InitTools";

    public static Application app;
    private static boolean isDebug;

    /**
     * 在application中初始化
     */
    public static void initInApplication(Application application) {
        app = application;
        isDebug = application.getPackageName().endsWith("debug");

        initReflect();
        initLogger();

    }

    /**
     * 在第一个Activity启动的时候初始化
     */
    public static void initInFirstActivity(Context context) {

    }

    /**
     * 应用彻底启动后，在闲置的时候初始化
     */
    public static void initInIdle(Context context) {

    }

    /**
     * 反射拿到主module里面的BuildConfig里面的属性，然后进行初始化
     */
    private static void initReflect() {
        try {
            String packageName = app.getPackageName().replace(".debug", "");
            Class aClass = Class.forName(packageName + ".BuildConfig");

            Object buildConfig = aClass.newInstance();
            String baseUrl = (String) aClass.getField("baseUrl").get(buildConfig);
            initRetrofit(baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化Retrofit
     *
     * @param baseUrl
     */
    private static void initRetrofit(String baseUrl) {

        RetrofitUtil.BASE_URL = baseUrl;


    }

    /**
     * 初始化二维码扫描
     */
    private static void initZXingLib() {
        ZXingLibrary.initDisplayOpinion(app);

    }

    /**
     * 初始化日志
     */
    private static void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return isDebug;
            }
        });

    }

}
