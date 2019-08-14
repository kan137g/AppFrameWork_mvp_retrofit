package com.vanke.handlecashregister;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.vanke.commonlib.InitTools;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2019-08-14.
 * PS: Not easy to write code, please indicate.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);


        InitTools.initInApplication(this);
    }
}
