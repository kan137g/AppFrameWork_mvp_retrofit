package com.vanke.commonlib.widget;

import android.text.TextUtils;
import android.widget.Toast;

import com.vanke.commonlib.InitTools;



/**
 * 防止重复toast，可自定义toast
 *
 * @author Super LiaoQ
 * @date 2015-4-20
 */
public class ToastUtils {
    private static Toast mToast = null;

    public static void toast(final String msg) {
        showToast(msg);
    }

    public static void debug(final String msg) {
        showToast("调试：" + msg);
    }


    /**
     * 防止重复toast
     *
     * @param msg
     */
    private static void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(InitTools.app, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}