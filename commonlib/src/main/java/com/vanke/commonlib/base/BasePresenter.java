package com.vanke.commonlib.base;

import android.app.Activity;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */
public class BasePresenter {

    //弱引用
    private WeakReference<Activity> weakReference;

    public BasePresenter(Activity activity) {
        weakReference = new WeakReference<>(activity);
    }

}
