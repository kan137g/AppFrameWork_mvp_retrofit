package com.kangwencai.appframework_mvp_retrofit;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kangwencai.appframework_mvp_retrofit.net.RetrofitRestApp;
import com.kangwencai.common.IConstants;
import com.kangwencai.common.net_retrofit.RetrofitRest;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */
public class MainApplication extends Application {
    private static MainApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        RetrofitRestApp.getInstance();

        if (IConstants.isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);

    }

    public static MainApplication getApplication() {
        return application;
    }
}
