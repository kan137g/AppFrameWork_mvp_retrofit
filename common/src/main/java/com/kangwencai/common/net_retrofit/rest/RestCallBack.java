package com.kangwencai.common.net_retrofit.rest;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.kangwencai.common.utils.LogUtils;
import com.kangwencai.common.utils.ToastUtils;
import com.kangwencai.common.widget.loadingview.CommitDataAnim;


import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Description: retrofit的数据请求回掉，返回的数据的主体为传输回来的字符串
 * <p>
 * Author: kangwencai
 * DATA: Date on 2018/6/25.
 * PS: Not easy to write code, please indicate.
 */
public abstract class RestCallBack implements Callback<ApiResponse> {
    /*系统错误*/
    private static final String SYS_ERROR = "SYS_ERROR";
    /*请求成功*/
    private static final String OK = "ok";
    /*定义一个静态常量专门来解析gson数据*/
    public static final Gson gson = new Gson();
    public static final String NET_ERROR = "网络异常";

    //    弱引用反正内存泄露
    private WeakReference<Context> mActivity;
    //    当前是否正在显示
    private boolean mIsProgressDialogShow;
    //    加载框可自己定义
    private CommitDataAnim mProgressDialog;

    public RestCallBack() {


    }

    public RestCallBack(Context context, int resId) {

        this.mActivity = new WeakReference<>(context);

        showProgressDialog(resId);
    }

    public RestCallBack(Context context, String msg) {

        this.mActivity = new WeakReference<>(context);

        showProgressDialog(msg);
    }

    /**
     * 请求成功，且返回正确数据
     *
     * @param result
     */
    public abstract void onSuccess(String result);

    /**
     * 请求失败
     *
     * @param msg
     */
    public abstract void onFailure(String msg);

    /**
     * 请求失败，可以考虑是否再次请求
     *
     * @param msg
     */
    public abstract void onNetError(String msg);


    @Override
    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
        ApiResponse apiResponse = response.body();
        if (null == apiResponse) {
            onFailure("请求失败");

            return;
        }
        LogUtils.e("本次收到的数据" + apiResponse.toString());
        String code = apiResponse.getCode();
        String msg = apiResponse.getSummary();

        if (OK.equalsIgnoreCase(code)) {

            if (null != apiResponse.getResult()) {
                onSuccess(apiResponse.getResult().toString());
            } else {
                onSuccess("");
            }

        } else {
//            ToastUtils.toast(msg);
            onFailure(msg);
        }

        dismissProgressDialog();
    }

    @Override
    public void onFailure(Call<ApiResponse> call, Throwable t) {
        dismissProgressDialog();
        onNetError(NET_ERROR);

        LogUtils.e(t.toString());
    }

    /**
     * 根据字符串来显示
     *
     * @param msg
     */
    public void showProgressDialog(String msg) {
        if (TextUtils.isEmpty(msg) || msg == null) {
            return;
        }
        showLoadingDialog(msg);
    }

    /**
     * 根据字符串引索值来显示
     *
     * @param resId
     */
    public void showProgressDialog(int resId) {
        if (resId <= 0) {
            return;
        }
        Context context = mActivity.get();
        String msg = context.getResources().getString(resId);
        showLoadingDialog(msg);
    }

    public void showProgressDialog() {
        Context context = mActivity.get();
//        String msg = context.getResources().getString(R.string.loading);
        String msg = "loading";
        showLoadingDialog(msg);
    }

    /**
     * 加载动画
     *
     * @param msg
     */
    private void showLoadingDialog(String msg) {
        try {
            if (mIsProgressDialogShow) {
                return;
            }
            if (mProgressDialog == null) {
                mProgressDialog = new CommitDataAnim(mActivity.get(), msg);
                //点击外面是否取消
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mIsProgressDialogShow = false;
                    }
                });
            } else {
                mProgressDialog.setContent(msg);
            }

            mProgressDialog.show();
            mIsProgressDialogShow = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void dismissProgressDialog() {
        if (mIsProgressDialogShow && mProgressDialog != null) {
            mProgressDialog.dismiss();
            mIsProgressDialogShow = false;
        }
    }


}