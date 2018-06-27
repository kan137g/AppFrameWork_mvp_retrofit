package com.kangwencai.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * 防止重复toast，可自定义toast
 *
 * @author Super LiaoQ
 * @date 2015-4-20
 */
public class ToastUtils {
    private static Toast mToast = null;

    public static void toast(Context context, String msg) {
        showToast(context, msg);
    }


    /**
     * 防止重复toast
     *
     * @param context
     * @param msg
     */
    private static void showToast(Context context, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}