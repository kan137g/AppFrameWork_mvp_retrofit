package com.kangwencai.common.utils;


import com.kangwencai.common.IConstants;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by wencai on 2015/10/30.
 * <p/>
 * 日志工具类
 */
public class LogUtils {
    //    private static boolean isDebug = false;
    private static boolean isDebug = IConstants.isDebug;

    /**
     * 2.2.0必须要初始化之后才能用，而且这里只能放到activity里面初始化
     */
    public static void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());

    }

    public static void turnOnDebug() {
        isDebug = true;
    }

    public static void turnOffDebug() {
        isDebug = false;
    }

    public static void json(String response) {
        if (isDebug) {
            Logger.json(response);
        }
    }

    public static void xml(String content) {
        if (isDebug) {
            Logger.xml(content);
        }
    }

    public static void wtf(String response) {
        if (isDebug) {
            Logger.wtf(response);
        }
    }

    public static void e(String content) {
        if (isDebug) {
            Logger.e(content);
        }
    }

    public static void i(String content) {
        if (isDebug) {
            Logger.i(content);
        }
    }

    public static void w(String content) {
        if (isDebug) {
            Logger.w(content);
        }
    }

    public static void v(String response) {
        if (isDebug) {
            Logger.v(response);
        }
    }


}
